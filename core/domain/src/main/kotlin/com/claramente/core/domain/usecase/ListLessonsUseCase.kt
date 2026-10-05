package com.claramente.core.domain.usecase

import com.claramente.core.data.contract.ILessonCatalogStore
import com.claramente.core.domain.contract.IListLessonsUseCase
import com.claramente.core.model.lesson.Lesson

class ListLessonsUseCase(private val catalogStore: ILessonCatalogStore) : IListLessonsUseCase {
    override suspend fun execute(): Result<List<Lesson>> = runCatching {
        catalogStore.loadAll()
    }
}
