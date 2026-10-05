package com.claramente.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.claramente.di.AppContainer
import com.claramente.feature.catalog.view.CatalogScreen
import com.claramente.feature.catalog.viewmodel.CatalogViewModel
import com.claramente.feature.hub.state.HubModules
import com.claramente.feature.hub.view.HubScreen
import com.claramente.feature.lesson.view.LessonScreen
import com.claramente.feature.lesson.viewmodel.LessonViewModel

@Composable
fun AppNavigation(container: AppContainer) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HUB) {
        composable(Routes.HUB) {
            HubScreen(
                modules = HubModules.all,
                onOpen = { module ->
                    when (module.key) {
                        HubModules.CATALOG -> navController.navigate(Routes.CATALOG)
                        HubModules.AR_TEST -> navController.navigate(Routes.lesson(Routes.AR_TEST_LESSON_ID))
                    }
                },
            )
        }
        composable(Routes.CATALOG) {
            val viewModel: CatalogViewModel = viewModel(
                factory = viewModelFactory { initializer { CatalogViewModel(container.listLessons) } },
            )
            val state by viewModel.state.collectAsState()
            CatalogScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onOpenLesson = { lesson -> navController.navigate(Routes.lesson(lesson.id)) },
                onRetry = viewModel::load,
            )
        }
        composable(
            route = Routes.LESSON,
            arguments = listOf(navArgument(Routes.LESSON_ID_ARG) { type = NavType.StringType }),
        ) { entry ->
            val lessonId = entry.arguments?.getString(Routes.LESSON_ID_ARG).orEmpty()
            val viewModel: LessonViewModel = viewModel(
                factory = viewModelFactory { initializer { LessonViewModel(container.findLesson, lessonId) } },
            )
            val state by viewModel.state.collectAsState()
            LessonScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onRetry = viewModel::load,
            )
        }
    }
}
