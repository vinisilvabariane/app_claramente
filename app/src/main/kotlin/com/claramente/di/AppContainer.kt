package com.claramente.di

import android.content.Context
import com.claramente.core.data.contract.ILessonCatalogStore
import com.claramente.core.data.store.SeedLessonCatalogStore
import com.claramente.core.domain.contract.IFindLessonUseCase
import com.claramente.core.domain.contract.IListLessonsUseCase
import com.claramente.core.domain.usecase.FindLessonUseCase
import com.claramente.core.domain.usecase.ListLessonsUseCase

class AppContainer(val context: Context) {
    val lessonCatalogStore: ILessonCatalogStore by lazy { SeedLessonCatalogStore() }

    val listLessons: IListLessonsUseCase by lazy { ListLessonsUseCase(lessonCatalogStore) }

    val findLesson: IFindLessonUseCase by lazy { FindLessonUseCase(lessonCatalogStore) }
}
