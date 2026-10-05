package com.claramente.core.data.contract

import com.claramente.core.model.lesson.Lesson

interface ILessonCatalogStore {
    suspend fun loadAll(): List<Lesson>
}
