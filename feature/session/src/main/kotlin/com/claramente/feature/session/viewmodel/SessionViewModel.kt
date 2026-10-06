package com.claramente.feature.session.viewmodel

import com.claramente.core.auth.controller.SessionController
import com.claramente.core.domain.contract.ILogoutUseCase
import com.claramente.core.domain.contract.IRestoreSessionUseCase
import com.claramente.feature.session.state.SessionUiState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SessionViewModel(
    private val sessions: SessionController,
    private val restoreSession: IRestoreSessionUseCase,
    private val logoutUser: ILogoutUseCase,
) : ViewModel() {
    private val loggingOut = MutableStateFlow(false)

    val state: StateFlow<SessionUiState> = combine(sessions.current, sessions.checked, loggingOut) { user, checked, busy ->
        SessionUiState(user = user, checked = checked, loggingOut = busy)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SessionUiState(user = sessions.current.value, checked = sessions.checked.value),
    )

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { restoreSession.execute() }
            result.onFailure { Log.w(TAG, "session restore failed", it) }
        }
    }

    fun logout() {
        if (loggingOut.value) return
        loggingOut.value = true
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { logoutUser.execute() }
            result.onFailure { Log.w(TAG, "logout failed", it) }
            loggingOut.value = false
        }
    }

    private companion object {
        const val TAG = "ClaramenteSession"
    }
}
