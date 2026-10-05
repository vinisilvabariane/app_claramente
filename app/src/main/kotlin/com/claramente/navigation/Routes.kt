package com.claramente.navigation

object Routes {
    const val HUB = "hub"
    const val CATALOG = "catalog"
    const val LESSON_ID_ARG = "lessonId"
    const val LESSON = "lesson/{$LESSON_ID_ARG}"
    const val AR_TEST_LESSON_ID = "cube"

    fun lesson(id: String): String = "lesson/$id"
}
