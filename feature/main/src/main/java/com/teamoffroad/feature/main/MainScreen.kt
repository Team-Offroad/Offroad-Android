package com.teamoffroad.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.TYPE_ANNOUNCEMENT
import com.teamoffroad.core.common.util.OnBackButtonListener
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
            if (notificationType == TYPE_ANNOUNCEMENT)
                notificationId?.let {
                    navigator.navigateToMyPage()
                    navigator.navigateToSetting()
                    navigator.navigateToAnnouncement(it)
                }
            else {
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
                modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            )
            OnBackButtonListener(
                navigator::popBackStackIfNotMainTabRoute,
                navigator.setBackButtonListenerEnabled(),
            )
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.setBottomBarVisibility(),
                tabs = MainNavTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) },
            )
        }
    )
}
