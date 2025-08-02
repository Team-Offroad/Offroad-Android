package com.teamoffroad.feature.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.DiaryRoute
import com.teamoffroad.core.navigation.RecommendPlaceRoute
import com.teamoffroad.feature.diary.presentation.DiaryScreen

fun NavController.navigateToDiary(newDiaryExist: Boolean, beforeNavigateRoute: String, characterName: String) {
    navigate(DiaryRoute.Diary(newDiaryExist, beforeNavigateRoute, characterName))
}

fun NavGraphBuilder.diaryNavGraph(
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (String) -> Unit,
    navigateToDiaryTime: () -> Unit,
) {
    composable<DiaryRoute.Diary> { backStackEntry ->
        val newDiaryExist = backStackEntry.toRoute<DiaryRoute.Diary>().newDiaryExist
        val beforeNavigateRoute = backStackEntry.toRoute<DiaryRoute.Diary>().beforeNavigateRoute
        val characterName = backStackEntry.toRoute<DiaryRoute.Diary>().characterName
        DiaryScreen(
            newDiaryExist = newDiaryExist,
            beforeNavigateRoute= beforeNavigateRoute,
            characterName = characterName,
            navigateToBack = navigateToBack,
            navigateToCharacterChat = navigateToCharacterChat,
            navigateToDiaryTime = navigateToDiaryTime,
        )
    }
}