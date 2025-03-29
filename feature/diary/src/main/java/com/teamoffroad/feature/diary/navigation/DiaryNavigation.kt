package com.teamoffroad.feature.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.DiaryRoute
import com.teamoffroad.feature.diary.presentation.DiaryScreen

fun NavController.navigateToDiary(newDiaryExist: Boolean) {
    navigate(DiaryRoute.Diary(newDiaryExist))
}

fun NavGraphBuilder.diaryNavGraph(
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (String) -> Unit,
    navigateToDiaryTime: () -> Unit,
) {
    composable<DiaryRoute.Diary> { backStackEntry ->
        val newDiaryExist = backStackEntry.toRoute<DiaryRoute.Diary>().newDiaryExist
        DiaryScreen(
            newDiaryExist = newDiaryExist,
            navigateToBack = navigateToBack,
            navigateToCharacterChat = navigateToCharacterChat,
            navigateToDiaryTime = navigateToDiaryTime,
        )
    }
}