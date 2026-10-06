package com.claramente.core.domain.usecase

import com.claramente.core.auth.controller.SessionController
import com.claramente.core.auth.model.UserSession
import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.domain.contract.ILoginUseCase
import com.claramente.core.network.contract.IAuthClient

class LoginUseCase(
    private val authClient: IAuthClient,
    private val tokenStore: ITokenStore,
    private val sessions: SessionController,
) : ILoginUseCase {
    override suspend fun execute(email: String, password: String): Result<UserSession> = runCatching {
        val auth = authClient.login(email, password)
        val session = sessions.open(auth)
        tokenStore.save(auth.snapshot())
        session
    }
}
