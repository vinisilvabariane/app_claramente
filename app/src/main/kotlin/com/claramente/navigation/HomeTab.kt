package com.claramente.navigation

enum class HomeTab(val route: String, val label: String) {
    HUB(Routes.HUB, "Início"),
    PROFILE(Routes.PROFILE, "Perfil"),
    AR(Routes.AR, "AR");

    companion object {
        fun fromRoute(route: String?): HomeTab = entries.firstOrNull { it.route == route } ?: HUB
    }
}
