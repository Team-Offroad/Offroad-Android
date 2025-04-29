package com.teamoffroad.feature.recommendplace.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.RecommendPlaceRoute
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceOrder
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceScreen

fun NavController.navigateToRecommendPlace(hasChatted: Boolean) {
    navigate(RecommendPlaceRoute.RecommendPlace(hasChatted))
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
        RecommendPlaceScreen(hasChatted, navigateToBack, navigateToOrderRecommendPlace)
    }

    composable<RecommendPlaceRoute.OrderRecommendPlace> { backStackEntry ->
        RecommendPlaceOrder(navigateToBack)
    }
}