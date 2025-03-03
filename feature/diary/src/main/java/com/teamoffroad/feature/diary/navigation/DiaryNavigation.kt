package com.teamoffroad.feature.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.DiaryRoute
import com.teamoffroad.feature.diary.presentation.DiaryScreen

fun NavController.navigateToDiary() {
    navigate(DiaryRoute.Diary)
}

fun NavGraphBuilder.diaryNavGraph(
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (String) -> Unit,
    navigateToDiaryTime: () -> Unit,
) {
    composable<DiaryRoute.Diary> {
        DiaryScreen(
            navigateToBack = navigateToBack,
            navigateToCharacterChat = navigateToCharacterChat,
            navigateToDiaryTime = navigateToDiaryTime,
        )
    }
}