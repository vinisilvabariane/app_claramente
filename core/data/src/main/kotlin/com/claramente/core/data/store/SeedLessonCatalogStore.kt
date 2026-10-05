package com.claramente.core.data.store

import com.claramente.core.data.contract.ILessonCatalogStore
import com.claramente.core.model.lesson.Lesson
import com.claramente.core.model.lesson.LessonShape

class SeedLessonCatalogStore : ILessonCatalogStore {
    override suspend fun loadAll(): List<Lesson> = listOf(
        Lesson(
            id = "cube",
            title = "Cubo",
            summary = "6 faces quadradas, 12 arestas e 8 vértices. Todas as arestas têm o mesmo tamanho.",
            shape = LessonShape.CUBE,
        ),
        Lesson(
            id = "sphere",
            title = "Esfera",
            summary = "Todos os pontos da superfície ficam à mesma distância do centro: o raio.",
            shape = LessonShape.SPHERE,
        ),
        Lesson(
            id = "cylinder",
            title = "Cilindro",
            summary = "Duas bases circulares paralelas ligadas por uma superfície lateral curva.",
            shape = LessonShape.CYLINDER,
        ),
    )
}
