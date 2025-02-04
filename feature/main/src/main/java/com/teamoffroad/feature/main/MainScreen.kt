package com.teamoffroad.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.characterchat.presentation.MainCharacterChatViewModel
import com.teamoffroad.characterchat.presentation.component.ShowUserChat
import com.teamoffroad.characterchat.presentation.model.CharacterChattingUiState
import com.teamoffroad.characterchat.presentation.model.UserChattingUiState
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
    mainViewModel: MainViewModel,
    mainCharacterViewModel: MainCharacterChatViewModel,
) {
    val mainContainerSetting = remember { mutableStateOf(false) }
    val isMainUiState by mainViewModel.mainUiState.collectAsState()
    val characterChatUiState = mainCharacterViewModel.characterChatUiState.collectAsStateWithLifecycle()
    val userChatUiState = mainCharacterViewModel.userChatUiState.collectAsStateWithLifecycle()
    val userChattingText = mainCharacterViewModel.userChattingText.collectAsStateWithLifecycle()

    if (mainContainerSetting.value) {
        LaunchedEffect(isMainUiState) {
            if (!isMainUiState.announcementId.isNullOrBlank()) {
                navigator.navigateToAnnouncement(isMainUiState.announcementId)
            }
            mainViewModel.initState()
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
        userChattingText = userChattingText,
        modifier = modifier,
        mainContainerSetting = mainContainerSetting,
        characterChatUiState = characterChatUiState,
        userChatUiState = userChatUiState,
        updateAnswerCharacterChatButtonState = mainCharacterViewModel::updateAnswerCharacterChatButtonState,
        updateCharacterChatExist = mainCharacterViewModel::updateCharacterChatExist,
        updateUserWatchingCharacterChat = mainCharacterViewModel::updateUserWatchingCharacterChat,
        updateUserChattingText = mainCharacterViewModel::updateUserChattingText,
        updateShowUserChatTextField = mainCharacterViewModel::updateShowUserChatTextField,
        sendChat = mainCharacterViewModel::sendChat,
        navigateToCharacterChatScreen = navigator::navigateToCharacterChat
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    userChattingText: State<String>,
    mainContainerSetting: MutableState<Boolean>,
    characterChatUiState: State<CharacterChattingUiState>,
    userChatUiState: State<UserChattingUiState>,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateUserChattingText: (String) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    sendChat: () -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit,
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

//                    showCharacterChat(
//                        characterChatUiState = characterChatUiState,
//                        userChatUiState = userChatUiState,
//                        updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
//                        updateCharacterChatExist = updateCharacterChatExist,
//                        updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
//                        updateShowUserChatTextField = updateShowUserChatTextField,
//                        navigateToCharacterChatScreen = navigateToCharacterChatScreen
//                    )

                    ShowUserChat(
                        userChatUiState = userChatUiState,
                        characterChatUiState = characterChatUiState,
                        userChattingText = userChattingText,
                        updateCharacterChatExist = updateCharacterChatExist,
                        updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                        updateUserChattingText = updateUserChattingText,
                        updateShowUserChatTextField = updateShowUserChatTextField,
                        sendChat = sendChat
                    )
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
