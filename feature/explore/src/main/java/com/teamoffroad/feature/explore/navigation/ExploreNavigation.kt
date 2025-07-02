package com.teamoffroad.feature.explore.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.ExploreRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.CourseQuestDetailScreen
import com.teamoffroad.feature.explore.presentation.ExploreScreen
import com.teamoffroad.feature.explore.presentation.PlaceScreen
import com.teamoffroad.feature.explore.presentation.QuestScreen
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState

fun NavController.navigateToExplore(
    authResultType: String = ExploreAuthState.None.toString(),
    navOptions: NavOptions,
) {
    navigate(MainTabRoute.Explore(authResultType), navOptions)
}

fun NavController.navigateToPlace(
    latitude: String,
    longitude: String,
) {
    navigate(ExploreRoute.PlaceScreen(latitude, longitude))
}

fun NavController.navigateToQuest() {
    navigate(ExploreRoute.QuestScreen)
}

fun NavController.navigateToCourseQuestDetail(
    questId: Long,
    deadline: String,
    dDay: Int,
) {
    navigate(ExploreRoute.CourseQuestDetail(questId, deadline, dDay))
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: () -> Unit,
    navigateToPlace: (String, String) -> Unit,
    navigateToQuest: () -> Unit,
    navigateToQuestDetail: (questId: Long, deadline: String, dDay: Int) -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.Explore> { backStackEntry ->
        val authResultState = backStackEntry.toRoute<MainTabRoute.Explore>().authResultState
        ExploreScreen(authResultState, navigateToHome, navigateToPlace, navigateToQuest)
    }

    composable<ExploreRoute.PlaceScreen> { backStackEntry ->
        val latitude = backStackEntry.toRoute<ExploreRoute.PlaceScreen>().latitude
        val longitude = backStackEntry.toRoute<ExploreRoute.PlaceScreen>().longitude
        PlaceScreen(latitude, longitude, navigateToBack)
    }

    composable<ExploreRoute.QuestScreen> {
        QuestScreen(navigateToQuestDetail, navigateToBack)
    }

    composable<ExploreRoute.CourseQuestDetail> { backStackEntry ->
        val questId = backStackEntry.toRoute<ExploreRoute.CourseQuestDetail>().questId
        val deadline = backStackEntry.toRoute<ExploreRoute.CourseQuestDetail>().deadline
        val dDay = backStackEntry.toRoute<ExploreRoute.CourseQuestDetail>().dDay
        CourseQuestDetailScreen(questId, deadline, dDay, navigateToBack)
    }
}
