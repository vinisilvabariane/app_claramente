package com.claramente.core.network.http

import com.claramente.core.model.auth.AuthTokens

class AuthSession(
    @Volatile var token: String,
    @Volatile var refreshToken: String,
    @Volatile var refreshExpires: String,
) {
    fun snapshot(): AuthTokens = AuthTokens(token, refreshToken, refreshExpires)

    companion object {
        fun from(tokens: AuthTokens): AuthSession = AuthSession(tokens.token, tokens.refreshToken, tokens.refreshExpires)
    }
}
