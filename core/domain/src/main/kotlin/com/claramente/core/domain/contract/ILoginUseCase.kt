package com.claramente.core.domain.contract

import com.claramente.core.auth.model.UserSession

interface ILoginUseCase {
    suspend fun execute(email: String, password: String): Result<UserSession>
}
