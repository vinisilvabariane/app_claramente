package com.claramente.core.auth.mapper

import org.json.JSONObject

internal object JwtClaimsMapper {
    fun payload(token: String): JSONObject? = runCatching {
        val body = token.split('.')[1]
        JSONObject(String(Base64UrlMapper.decode(body), Charsets.UTF_8))
    }.getOrNull()

    fun text(claims: JSONObject?, key: String): String? =
        claims?.takeIf { it.has(key) && !it.isNull(key) }?.optString(key)?.trim()?.takeIf { it.isNotEmpty() }

    fun list(claims: JSONObject?, key: String): List<String> {
        val source = claims?.takeIf { it.has(key) && !it.isNull(key) } ?: return emptyList()
        val array = source.optJSONArray(key) ?: return listOfNotNull(text(source, key))
        return (0 until array.length()).mapNotNull { position ->
            array.optString(position).trim().takeIf { role -> role.isNotEmpty() }
        }
    }

    fun expiresAtMs(claims: JSONObject?): Long? =
        claims?.takeIf { it.has("exp") && !it.isNull("exp") }?.optLong("exp")?.takeIf { it > 0 }?.times(1000)
}
