package com.claramente.feature.ar.state

import java.io.File
import java.security.MessageDigest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageTargetsIntegrityTest {

    private val assetsDir: File = generateSequence(File(System.getProperty("user.dir")).absoluteFile) { it.parentFile }
        .map { File(it, "src/main/assets") }
        .firstOrNull { File(it, "ar").isDirectory }
        ?: File(System.getProperty("user.dir"), "src/main/assets")

    private fun sha256(path: String): String =
        MessageDigest.getInstance("SHA-256")
            .digest(File(assetsDir, path).readBytes())
            .joinToString("") { "%02x".format(it) }

    @Test
    fun markerImageNeverChanges() {
        assertEquals(EXPECTED_MARKER_SHA256, sha256(ImageTargets.marker.assetPath))
    }

    @Test
    fun cubeFaceImagesNeverChange() {
        assertEquals(EXPECTED_FACE_SHA256.size, ImageTargets.cubeFaces.size)
        ImageTargets.cubeFaces.forEachIndexed { index, face ->
            assertEquals("hash da ${face.name}", EXPECTED_FACE_SHA256[index], sha256(face.assetPath))
        }
    }

    @Test
    fun markerDefinitionNeverChanges() {
        assertEquals("flat-marker", ImageTargets.marker.name)
        assertEquals("ar/marker.jpg", ImageTargets.marker.assetPath)
        assertEquals(0.12f, ImageTargets.marker.widthMeters, 0f)
    }

    @Test
    fun cubeDefinitionNeverChanges() {
        assertEquals(6, ImageTargets.cubeFaces.size)
        ImageTargets.cubeFaces.forEachIndexed { index, face ->
            val number = index + 1
            assertEquals("cube-face-$number", face.name)
            assertEquals("ar/cube-face-$number.jpg", face.assetPath)
            assertEquals(0.05f, face.widthMeters, 0f)
        }
    }

    @Test
    fun everyModeKeepsItsTargets() {
        assertEquals(listOf(ImageTargets.marker), ImageTargets.forMode(ArMode.MARKER))
        assertEquals(ImageTargets.cubeFaces, ImageTargets.forMode(ArMode.CUBE))
        assertTrue(ImageTargets.forMode(ArMode.QR).isEmpty())
    }

    private companion object {
        const val EXPECTED_MARKER_SHA256 = "b17aa6061757ddd10eea285ea9a0a2e167fea3818d828e1415e481fce9ca1489"

        val EXPECTED_FACE_SHA256 = listOf(
            "51bbf027bcb78e0ab282d36f84cffebd90dfca1abd4edd4f0487740b2a0946aa",
            "0379a90d528d43632db101cc63850ef83084934e3b0a69cd0d3ead8e538537c0",
            "cb1f44a5de127292b3333d9554c5a8b715e174c29957df90188a7913aa0a3fc7",
            "19bb2bae097600e802dc5bfb667af2d9bd98044d7f7d405a31a907e4c9c0d3fb",
            "6e42007621bd4248df0fa1fc27fc21c9170b76dc19434b283f90a7f21360ca9a",
            "091edc74d88ad234e4c2f772187c5ab2633ba37902900d9c1cea7b3cbddb878f",
        )
    }
}
