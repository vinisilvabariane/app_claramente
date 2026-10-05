package com.claramente.core.domain.contract

import com.claramente.core.model.lesson.Lesson

interface IListLessonsUseCase {
    suspend fun execute(): Result<List<Lesson>>
}
