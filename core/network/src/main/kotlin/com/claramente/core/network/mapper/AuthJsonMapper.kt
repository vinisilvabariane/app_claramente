package com.claramente.core.network.mapper

import com.claramente.core.network.http.AuthSession
import org.json.JSONObject

internal object AuthJsonMapper {
    fun session(json: JSONObject): AuthSession = AuthSession(
        token = json.optString("token"),
        refreshToken = json.optString("refreshToken"),
        refreshExpires = json.optString("refreshExpires"),
    )
}
