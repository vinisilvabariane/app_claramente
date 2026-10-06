package com.claramente.feature.session.state

import com.claramente.core.auth.model.UserSession

data class SessionUiState(
    val user: UserSession? = null,
    val checked: Boolean = false,
    val loggingOut: Boolean = false,
)
