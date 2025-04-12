package com.teamoffroad.feature.recommendplace.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.RecommendPlaceRoute
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceOrder
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceScreen

fun NavController.navigateToRecommendPlace() {
    navigate(RecommendPlaceRoute.RecommendPlace)
}

fun NavController.navigateToOrderRecommendPlace() {
    navigate(RecommendPlaceRoute.OrderRecommendPlace)
}

fun NavGraphBuilder.recommendPlaceNavGraph(
    navigateToBack: () -> Unit,
    navigateToOrderRecommendPlace: () -> Unit
) {
    composable<RecommendPlaceRoute.RecommendPlace> { backStackEntry ->
        RecommendPlaceScreen(navigateToBack, navigateToOrderRecommendPlace)
    }
}

fun NavGraphBuilder.orderRecommendPlaceNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<RecommendPlaceRoute.OrderRecommendPlace> { backStackEntry ->
        RecommendPlaceOrder(navigateToBack)
    }
}