package com.claramente.core.domain.usecase

import com.claramente.core.auth.controller.SessionController
import com.claramente.core.auth.model.UserSession
import com.claramente.core.auth.policy.TokenValidityPolicy
import com.claramente.core.data.contract.ITokenStore
import com.claramente.core.domain.contract.IRestoreSessionUseCase
import com.claramente.core.network.contract.IAuthClient
import com.claramente.core.network.http.ApiException
import com.claramente.core.network.http.AuthSession

class RestoreSessionUseCase(
    private val authClient: IAuthClient,
    private val tokenStore: ITokenStore,
    private val sessions: SessionController,
) : IRestoreSessionUseCase {
    override suspend fun execute(): Result<UserSession?> = runCatching {
        try {
            restore()
        } finally {
            sessions.markChecked()
        }
    }

    private fun restore(): UserSession? {
        val tokens = tokenStore.load() ?: return null
        if (TokenValidityPolicy.isValid(tokens.token, System.currentTimeMillis())) {
            return sessions.open(AuthSession.from(tokens))
        }
        val refreshed = try {
            authClient.refresh(tokens.refreshToken)
        } catch (error: ApiException) {
            if (error.status == 401 || error.status == 403) tokenStore.clear()
            return null
        } catch (error: Exception) {
            return null
        }
        val session = sessions.open(refreshed)
        tokenStore.save(refreshed.snapshot())
        return session
    }
}
