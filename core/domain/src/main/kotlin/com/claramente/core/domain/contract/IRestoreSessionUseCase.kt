package com.claramente.core.domain.contract

import com.claramente.core.auth.model.UserSession

interface IRestoreSessionUseCase {
    suspend fun execute(): Result<UserSession?>
}
