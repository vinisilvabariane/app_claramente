package com.claramente.feature.ar.state

data class ArUiState(
    val models: List<ArModel>,
    val selected: ArModel,
    val arWebUrl: String?,
    val mode: ArMode = ArMode.MARKER,
) {
    val modes: List<ArMode>
        get() = ArMode.entries

    val experienceUrl: String?
        get() = arWebUrl?.trimEnd('/')?.let { "$it/ar/index.html?modelo=${selected.id}" }
}
