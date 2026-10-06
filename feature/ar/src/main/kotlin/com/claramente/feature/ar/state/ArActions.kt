package com.claramente.feature.ar.state

data class ArActions(
    val onSelect: (ArModel) -> Unit,
    val onSelectMode: (ArMode) -> Unit,
    val onStart: () -> Unit,
    val onOpenWeb: () -> Unit,
)
