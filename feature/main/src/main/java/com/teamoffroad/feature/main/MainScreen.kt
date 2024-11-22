package com.teamoffroad.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.TYPE_ANNOUNCEMENT
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.feature.main.component.MainBottomBar
import com.teamoffroad.feature.main.component.MainNavHost
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
    notificationType: String?,
    notificationId: String?,
    viewModel: MainViewModel,
) {
    val mainContainerSetting = remember { mutableStateOf(false) }
    val isMainUiState by viewModel.mainUiState.collectAsState()

    if (mainContainerSetting.value) {
        LaunchedEffect(isMainUiState) {
            if (!isMainUiState.announcementId.isNullOrBlank()) {
                navigator.navigateToAnnouncement(isMainUiState.announcementId)
            }
            viewModel.initState()
        }
        LaunchedEffect(notificationType, notificationId) {
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
    }
    MainScreenContent(
        navigator = navigator,
        modifier = modifier,
        mainContainerSetting = mainContainerSetting,
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    mainContainerSetting: MutableState<Boolean>
) {
    val showSplash = remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(1550)
        showSplash.value = false
    }
    Scaffold(
        modifier = modifier,
        content = { padding ->
            when (showSplash.value) {
                true -> SplashScreen()
                false -> {
                    MainNavHost(
                        navigator = navigator,
                        padding = padding,
                        modifier = Modifier,
                    )
                    OnBackButtonListener(
                        navigator::popBackStackIfNotMainTabRoute,
                        navigator.setBackButtonListenerEnabled(),
                    )
                    mainContainerSetting.value = true
                }
            }
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
