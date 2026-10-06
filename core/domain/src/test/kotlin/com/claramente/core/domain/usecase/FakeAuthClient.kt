package com.claramente.core.domain.usecase

import com.claramente.core.network.contract.IAuthClient
import com.claramente.core.network.http.AuthSession

internal class FakeAuthClient(
    private val issued: AuthSession = AuthSession("token", "refresh", "expires"),
    private val refreshFailure: Exception? = null,
    private val logoutFailure: Exception? = null,
) : IAuthClient {
    val calls = mutableListOf<String>()

    override fun login(email: String, password: String): AuthSession {
        calls += "login:$email"
        return issued
    }

    override fun refresh(refreshToken: String): AuthSession {
        calls += "refresh:$refreshToken"
        refreshFailure?.let { throw it }
        return issued
    }

    override fun logout(session: AuthSession) {
        calls += "logout"
        logoutFailure?.let { throw it }
    }
}
