package com.claramente.feature.login.state

data class LoginActions(
    val onEmail: (String) -> Unit,
    val onPassword: (String) -> Unit,
    val onSubmit: () -> Unit,
    val onMockLogin: () -> Unit,
)
