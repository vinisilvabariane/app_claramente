package com.claramente.core.domain.usecase

import com.claramente.core.data.contract.ILessonCatalogStore
import com.claramente.core.domain.error.LessonErrors
import com.claramente.core.model.lesson.Lesson
import com.claramente.core.model.lesson.LessonShape
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LessonUseCasesTest {
    private class FakeLessonCatalogStore(
        private val lessons: List<Lesson>,
        private val failure: Exception? = null,
    ) : ILessonCatalogStore {
        override suspend fun loadAll(): List<Lesson> {
            failure?.let { throw it }
            return lessons
        }
    }

    private val cube = Lesson("cube", "Cubo", "resumo do cubo", LessonShape.CUBE)
    private val sphere = Lesson("sphere", "Esfera", "resumo da esfera", LessonShape.SPHERE)

    @Test
    fun `listar devolve as licoes do store`() = runBlocking {
        val result = ListLessonsUseCase(FakeLessonCatalogStore(listOf(cube, sphere))).execute()
        assertEquals(listOf(cube, sphere), result.getOrThrow())
    }

    @Test
    fun `listar devolve falha quando o store falha`() = runBlocking {
        val result = ListLessonsUseCase(FakeLessonCatalogStore(emptyList(), IllegalStateException("disco"))).execute()
        assertTrue(result.isFailure)
    }

    @Test
    fun `buscar devolve a licao pelo id`() = runBlocking {
        val result = FindLessonUseCase(FakeLessonCatalogStore(listOf(cube, sphere))).execute("sphere")
        assertEquals(sphere, result.getOrThrow())
    }

    @Test
    fun `buscar falha com a mensagem de nao encontrada`() = runBlocking {
        val result = FindLessonUseCase(FakeLessonCatalogStore(listOf(cube))).execute("pyramid")
        assertEquals(LessonErrors.NOT_FOUND, result.exceptionOrNull()?.message)
    }
}
