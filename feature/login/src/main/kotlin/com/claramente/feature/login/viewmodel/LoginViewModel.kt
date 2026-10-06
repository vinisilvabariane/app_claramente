package com.claramente.feature.login.viewmodel

import com.claramente.core.domain.contract.ILoginUseCase
import com.claramente.core.domain.contract.IMockLoginUseCase
import com.claramente.core.domain.error.AuthErrors
import com.claramente.core.network.http.ApiException
import com.claramente.feature.login.state.LoginUiState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class LoginViewModel(
    private val loginUser: ILoginUseCase,
    private val mockLoginUser: IMockLoginUseCase,
    mockEnabled: Boolean,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState(mockEnabled = mockEnabled))
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun onEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun submit() {
        val current = _state.value
        if (!current.canSubmit) return
        _state.update { it.copy(loading = true, error = null) }
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { loginUser.execute(current.email.trim(), current.password) }
            result.fold(
                onSuccess = { _state.update { it.copy(email = "", password = "", loading = false, error = null) } },
                onFailure = { error ->
                    Log.w(TAG, "login failed", error)
                    _state.update { it.copy(loading = false, error = describe(error)) }
                },
            )
        }
    }

    fun mockLogin() {
        if (_state.value.loading) return
        _state.update { it.copy(error = null) }
        val email = _state.value.email.trim().ifBlank { null }
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { mockLoginUser.execute(email) }
            result.fold(
                onSuccess = { _state.update { it.copy(email = "", password = "", loading = false, error = null) } },
                onFailure = { error ->
                    Log.w(TAG, "mock login failed", error)
                    _state.update { it.copy(error = describe(error)) }
                },
            )
        }
    }

    private fun describe(error: Throwable): String = when (error) {
        is ApiException -> error.message ?: AuthErrors.REQUEST_FAILED
        is IOException -> AuthErrors.REQUEST_FAILED
        else -> AuthErrors.UNEXPECTED
    }

    private companion object {
        const val TAG = "ClaramenteLogin"
    }
}
