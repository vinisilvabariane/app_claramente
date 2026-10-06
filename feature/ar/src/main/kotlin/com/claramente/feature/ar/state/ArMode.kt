package com.claramente.feature.ar.state

enum class ArMode(val title: String, val caption: String, val hint: String) {
    MARKER("Marcador", "Folha impressa", "Aponte para a folha impressa do marcador"),
    CUBE("Cubo", "Merge Cube", "Gire o cubo na mão para ver de outros ângulos"),
    QR("QR code", "Sem marcador", "Aponte para um QR code do material"),
}
