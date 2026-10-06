package com.claramente.core.network.http

import android.util.Base64
import org.json.JSONObject

object TokenExpiryReader {
    fun isExpired(token: String, nowMs: Long): Boolean {
        val claims = payload(token) ?: return true
        val exp = (claims.opt("exp") as? Number)?.toLong() ?: return false
        return exp * 1000 <= nowMs
    }

    private fun payload(token: String): JSONObject? = runCatching {
        val body = token.split('.')[1]
        JSONObject(String(Base64.decode(body, Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP), Charsets.UTF_8))
    }.getOrNull()
}
