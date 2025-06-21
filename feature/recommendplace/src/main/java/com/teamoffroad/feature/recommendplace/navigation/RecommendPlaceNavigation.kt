package com.teamoffroad.feature.recommendplace.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.RecommendPlaceRoute
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceOrder
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceScreen

fun NavController.navigateToRecommendPlace(hasChatted: Boolean, name: String, content: String) {
    navigate(RecommendPlaceRoute.RecommendPlace(hasChatted, name, content))
}

fun NavController.navigateToOrderRecommendPlace() {
    navigate(RecommendPlaceRoute.OrderRecommendPlace)
}

fun NavGraphBuilder.recommendPlaceNavGraph(
    navigateToBack: () -> Unit,
    navigateToOrderRecommendPlace: () -> Unit
) {
    composable<RecommendPlaceRoute.RecommendPlace> { backStackEntry ->
        val hasChatted = backStackEntry.toRoute<RecommendPlaceRoute.RecommendPlace>().hasChatted
        val characterName = backStackEntry.toRoute<RecommendPlaceRoute.RecommendPlace>().name
        val content = backStackEntry.toRoute<RecommendPlaceRoute.RecommendPlace>().content
        RecommendPlaceScreen(hasChatted, characterName, content, navigateToBack, navigateToOrderRecommendPlace)
    }

    composable<RecommendPlaceRoute.OrderRecommendPlace> { backStackEntry ->
        RecommendPlaceOrder(navigateToBack)
    }
}