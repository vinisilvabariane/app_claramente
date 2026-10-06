package com.claramente.core.model.auth

data class AuthTokens(val token: String, val refreshToken: String, val refreshExpires: String)
