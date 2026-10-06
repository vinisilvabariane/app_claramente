package com.claramente.core.network.client

import com.claramente.core.network.contract.IAuthClient
import com.claramente.core.network.http.AuthSession
import com.claramente.core.network.http.ClaramenteHttp
import com.claramente.core.network.mapper.AuthJsonMapper
import org.json.JSONObject

class HttpAuthClient(private val http: ClaramenteHttp) : IAuthClient {
    override fun login(email: String, password: String): AuthSession {
        val body = JSONObject().put("email", email).put("password", password)
        return AuthJsonMapper.session(http.post("/auth/login", body, bearer = null))
    }

    override fun refresh(refreshToken: String): AuthSession {
        val body = JSONObject().put("refreshToken", refreshToken)
        return AuthJsonMapper.session(http.post("/auth/refresh", body, bearer = null))
    }

    override fun logout(session: AuthSession) {
        http.authenticated(session) { token ->
            http.send("POST", "/auth/logout", JSONObject().put("refreshToken", session.refreshToken), token)
        }
    }
}
