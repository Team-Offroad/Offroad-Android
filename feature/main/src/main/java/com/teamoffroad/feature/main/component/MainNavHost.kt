package com.teamoffroad.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.teamoffroad.feature.auth.presentation.navigation.authNavGraph
import com.teamoffroad.feature.explore.presentation.navigation.exploreNavGraph
import com.teamoffroad.feature.home.navigation.homeNavGraph
import com.teamoffroad.feature.main.MainNavigator
import com.teamoffroad.feature.mypage.navigation.mypageNavGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
            )
            exploreNavGraph(
                navigateToHome = { navigator.navigateToHome() }
            )
            mypageNavGraph(
                padding = padding,
            )
            authNavGraph(
                padding = padding,
                navigateToHome = { navigator.navigateToHome() }
            )
        }
    }
}