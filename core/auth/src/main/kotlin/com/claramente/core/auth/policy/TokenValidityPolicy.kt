package com.claramente.core.auth.policy

import com.claramente.core.auth.mapper.JwtClaimsMapper

object TokenValidityPolicy {
    fun isValid(token: String, nowMs: Long): Boolean {
        val expiresAt = JwtClaimsMapper.expiresAtMs(JwtClaimsMapper.payload(token)) ?: return false
        return expiresAt > nowMs
    }
}
