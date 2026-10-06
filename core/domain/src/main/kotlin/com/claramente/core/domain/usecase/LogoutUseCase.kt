package com.claramente.core.domain.usecase

import com.claramente.core.auth.controller.SessionController
import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.domain.contract.ILogoutUseCase
import com.claramente.core.network.contract.IAuthClient

class LogoutUseCase(
    private val authClient: IAuthClient,
    private val tokenStore: ITokenStore,
    private val sessions: SessionController,
) : ILogoutUseCase {
    override suspend fun execute(): Result<Unit> = runCatching {
        sessions.auth?.let { session -> runCatching { authClient.logout(session) } }
        tokenStore.clear()
        sessions.clear()
    }
}
