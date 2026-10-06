package com.claramente.core.network.http

import com.claramente.core.network.mapper.AuthJsonMapper
import java.util.concurrent.TimeUnit
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ClaramenteHttp(
    private val baseUrl: String,
    private val onRefreshed: (AuthSession) -> Unit = {},
    private val onRejected: () -> Unit = {},
) {
    private val apiUrl = baseUrl.trimEnd('/') + "/api"

    private val publicClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .callTimeout(15, TimeUnit.SECONDS)
        .build()

    private val client = publicClient.newBuilder()
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .callTimeout(90, TimeUnit.SECONDS)
        .build()

    internal fun post(path: String, body: JSONObject, bearer: String?): JSONObject {
        val builder = Request.Builder()
            .url(apiUrl + path)
            .post(body.toString().toRequestBody(JSON))
        if (bearer != null) builder.header("Authorization", "Bearer $bearer")
        return execute(builder.build(), authenticated = bearer != null).let { if (it.isBlank()) JSONObject() else JSONObject(it) }
    }

    internal fun send(method: String, path: String, body: JSONObject?, bearer: String): String {
        val builder = Request.Builder()
            .url(apiUrl + path)
            .method(method, body?.toString()?.toRequestBody(JSON))
            .header("Authorization", "Bearer $bearer")
        return execute(builder.build(), authenticated = true)
    }

    internal fun <T> authenticated(session: AuthSession, call: (String) -> T): T {
        val used = session.token
        if (isExpired(used)) {
            renewOrReject(session, used)
            return call(session.token)
        }
        return try {
            call(used)
        } catch (expired: ApiException) {
            if (expired.status != 401) throw expired
            renewOrReject(session, used)
            call(session.token)
        }
    }

    private fun refresh(session: AuthSession): Boolean {
        val json = try {
            post("/auth/refresh", JSONObject().put("refreshToken", session.refreshToken), bearer = null)
        } catch (rejected: ApiException) {
            if (rejected.status == 401 || rejected.status == 403) return false
            throw rejected
        }
        val renewed = AuthJsonMapper.session(json)
        session.token = renewed.token
        session.refreshToken = renewed.refreshToken
        session.refreshExpires = renewed.refreshExpires
        onRefreshed(session)
        return true
    }

    private fun renewOrReject(session: AuthSession, staleToken: String) {
        if (refreshOnce(session, staleToken)) return
        onRejected()
        throw ApiException(401, SESSION_EXPIRED)
    }

    private fun refreshOnce(session: AuthSession, staleToken: String): Boolean = synchronized(session) {
        if (session.token != staleToken) return true
        refresh(session)
    }

    private fun isExpired(token: String): Boolean = TokenExpiryReader.isExpired(token, System.currentTimeMillis())

    private fun execute(request: Request, authenticated: Boolean): String {
        val chosen = if (authenticated) client else publicClient
        chosen.newCall(request).execute().use { response ->
            val text = response.body.string()
            if (!response.isSuccessful) {
                val envelope = runCatching { JSONObject(text) }.getOrNull()
                val message = envelope?.optString("error")?.takeIf { it.isNotEmpty() } ?: "HTTP ${response.code}"
                throw ApiException(response.code, message)
            }
            return text
        }
    }

    private companion object {
        val JSON = "application/json; charset=utf-8".toMediaType()
        const val SESSION_EXPIRED = "Sessão expirada. Entre de novo."
    }
}
