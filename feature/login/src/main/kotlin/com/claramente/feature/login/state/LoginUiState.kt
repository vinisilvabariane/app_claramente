package com.claramente.feature.login.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val loading: Boolean = false,
    val mockEnabled: Boolean = false,
) {
    val canSubmit: Boolean get() = email.isNotBlank() && password.isNotEmpty() && !loading
}
