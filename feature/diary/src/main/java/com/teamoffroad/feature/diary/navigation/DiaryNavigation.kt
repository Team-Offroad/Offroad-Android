package com.teamoffroad.feature.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.DiaryRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.diary.presentation.DiaryScreen

fun NavController.navigateToDiary() {
    navigate(DiaryRoute.Diary)
}

fun NavGraphBuilder.diaryNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<DiaryRoute.Diary> {
        DiaryScreen(
            navigateToBack = navigateToBack
        )
    }
}