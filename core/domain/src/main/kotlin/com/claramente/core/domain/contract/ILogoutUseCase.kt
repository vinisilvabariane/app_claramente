package com.claramente.core.domain.contract

interface ILogoutUseCase {
    suspend fun execute(): Result<Unit>
}
