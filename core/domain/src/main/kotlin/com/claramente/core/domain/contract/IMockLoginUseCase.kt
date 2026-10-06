package com.claramente.core.domain.contract

import com.claramente.core.auth.model.UserSession

interface IMockLoginUseCase {
    suspend fun execute(email: String?): Result<UserSession>
}
