package com.claramente.core.network.contract

import com.claramente.core.network.http.AuthSession

interface IAuthClient {
    fun login(email: String, password: String): AuthSession

    fun refresh(refreshToken: String): AuthSession

    fun logout(session: AuthSession)
}
