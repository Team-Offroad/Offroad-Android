package com.teamoffroad.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.feature.main.component.MainBottomBar
import com.teamoffroad.feature.main.component.MainNavHost
import kotlinx.collections.immutable.toPersistentList

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
    notificationType: String?,
    notificationId: String?,
) {
    LaunchedEffect(Unit) {
        if (!notificationType.isNullOrBlank()) {
            if (notificationType == "ANNOUNCEMENT_REDIRECT")
                notificationId?.let { navigator.navigateToAnnouncement(it) }
            else if (notificationType == "CHARACTER_CHAT") {
                navigator.navigateToHome()
            }
        }
    }
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
            OnBackButtonListener(
                navigator::popBackStackIfNotMainTabRoute,
                navigator.setBackButtonListenerEnabled(),
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationPadding()
                    .wrapContentHeight(),
                visible = navigator.setBottomBarVisibility(),
                tabs = MainNavTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}
