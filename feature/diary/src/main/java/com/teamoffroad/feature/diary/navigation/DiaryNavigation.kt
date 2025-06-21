package com.teamoffroad.feature.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.DiaryRoute
import com.teamoffroad.feature.diary.presentation.DiaryScreen

fun NavController.navigateToDiary(newDiaryExist: Boolean, characterName: String) {
    navigate(DiaryRoute.Diary(newDiaryExist, characterName))
}

fun NavGraphBuilder.diaryNavGraph(
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (String) -> Unit,
    navigateToDiaryTime: () -> Unit,
) {
    composable<DiaryRoute.Diary> { backStackEntry ->
        val newDiaryExist = backStackEntry.toRoute<DiaryRoute.Diary>().newDiaryExist
        val characterName = backStackEntry.toRoute<DiaryRoute.Diary>().characterName
        DiaryScreen(
            newDiaryExist = newDiaryExist,
            characterName = characterName,
            navigateToBack = navigateToBack,
            navigateToCharacterChat = navigateToCharacterChat,
            navigateToDiaryTime = navigateToDiaryTime,
        )
    }
}