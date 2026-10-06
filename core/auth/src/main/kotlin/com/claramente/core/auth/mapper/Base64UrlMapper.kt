package com.claramente.core.auth.mapper

internal object Base64UrlMapper {
    private const val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_"

    fun encode(bytes: ByteArray): String {
        val output = StringBuilder((bytes.size + 2) / 3 * 4)
        var index = 0
        while (index < bytes.size) {
            val first = bytes[index].toInt() and 0xFF
            val second = if (index + 1 < bytes.size) bytes[index + 1].toInt() and 0xFF else 0
            val third = if (index + 2 < bytes.size) bytes[index + 2].toInt() and 0xFF else 0
            val chunk = (first shl 16) or (second shl 8) or third
            val remaining = bytes.size - index
            output.append(ALPHABET[(chunk shr 18) and 63])
            output.append(ALPHABET[(chunk shr 12) and 63])
            if (remaining > 1) output.append(ALPHABET[(chunk shr 6) and 63])
            if (remaining > 2) output.append(ALPHABET[chunk and 63])
            index += 3
        }
        return output.toString()
    }

    fun decode(text: String): ByteArray {
        val clean = text.trim().trimEnd('=')
        val output = ByteArray(clean.length * 3 / 4)
        var written = 0
        var buffer = 0
        var bits = 0
        for (symbol in clean) {
            buffer = (buffer shl 6) or value(symbol)
            bits += 6
            if (bits >= 8) {
                bits -= 8
                output[written++] = ((buffer shr bits) and 0xFF).toByte()
            }
        }
        return output.copyOf(written)
    }

    private fun value(symbol: Char): Int = when (symbol) {
        '+' -> 62
        '/' -> 63
        else -> ALPHABET.indexOf(symbol).takeIf { it >= 0 } ?: throw IllegalArgumentException("Invalid base64url symbol: $symbol")
    }
}
