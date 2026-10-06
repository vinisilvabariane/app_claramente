package com.claramente.core.domain.usecase

import com.claramente.core.auth.controller.SessionController
import com.claramente.core.auth.model.UserSession
import com.claramente.core.auth.policy.MockTokenPolicy
import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.domain.contract.IMockLoginUseCase
import com.claramente.core.network.http.AuthSession

class MockLoginUseCase(
    private val tokenStore: ITokenStore,
    private val sessions: SessionController,
) : IMockLoginUseCase {
    override suspend fun execute(email: String?): Result<UserSession> = runCatching {
        val tokens = MockTokenPolicy.tokens(email, System.currentTimeMillis())
        val session = sessions.open(AuthSession.from(tokens))
        tokenStore.save(tokens)
        session
    }
}
