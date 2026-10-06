package com.claramente.feature.ar.state

object ArModels {
    private const val QR_PREFIX = "claramente:"

    val SOLAR = ArModel(
        id = "solar",
        title = "Sistema Solar",
        subject = "Ciências",
        description = "O Sol com Vênus, Terra e Marte orbitando em velocidades diferentes.",
    )
    val AGUA = ArModel(
        id = "agua",
        title = "Molécula de água",
        subject = "Química",
        description = "H₂O com o ângulo real de 104,5° entre os hidrogênios.",
    )
    val SOLIDOS = ArModel(
        id = "solidos",
        title = "Sólidos geométricos",
        subject = "Matemática",
        description = "Cubo, esfera e pirâmide lado a lado para comparar as formas.",
    )

    val all: List<ArModel> = listOf(SOLAR, AGUA, SOLIDOS)
    val default: ArModel = SOLAR

    fun resolveFromQr(data: String): ArModel? {
        val raw = data.trim().lowercase()
        val id = if (raw.startsWith(QR_PREFIX)) raw.removePrefix(QR_PREFIX) else raw
        return all.firstOrNull { it.id == id }
    }
}
