package com.claramente.core.domain.usecase

import com.claramente.core.data.contract.ILessonCatalogStore
import com.claramente.core.domain.contract.IFindLessonUseCase
import com.claramente.core.domain.error.LessonErrors
import com.claramente.core.model.lesson.Lesson

class FindLessonUseCase(private val catalogStore: ILessonCatalogStore) : IFindLessonUseCase {
    override suspend fun execute(id: String): Result<Lesson> = runCatching {
        catalogStore.loadAll().firstOrNull { it.id == id } ?: error(LessonErrors.NOT_FOUND)
    }
}
