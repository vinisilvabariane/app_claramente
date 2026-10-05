package com.claramente.feature.hub.state

object HubModules {
    const val CATALOG = "catalog"
    const val AR_TEST = "ar-test"

    val all: List<HubModule> = listOf(
        HubModule(CATALOG, "Lições", "Explore formas 3D no seu ambiente e aprenda geometria."),
        HubModule(AR_TEST, "Testar realidade aumentada", "Confira se o seu aparelho detecta superfícies e posiciona objetos."),
    )
}
