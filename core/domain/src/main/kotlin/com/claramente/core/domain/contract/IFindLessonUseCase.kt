package com.claramente.core.domain.contract

import com.claramente.core.model.lesson.Lesson

interface IFindLessonUseCase {
    suspend fun execute(id: String): Result<Lesson>
}
