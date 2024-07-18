package com.teamoffroad.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.main.component.MainBottomBar
import com.teamoffroad.feature.main.component.MainNavHost
import kotlinx.collections.immutable.toPersistentList

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    modifier: Modifier = Modifier
) {
    MainScreenContent(
        navigator = navigator,
        modifier = modifier,
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
) {
    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding,
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(74.dp)
                    .background(Sub4),
                visible = navigator.setBottomBarVisibility(),
                tabs = MainNavTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}