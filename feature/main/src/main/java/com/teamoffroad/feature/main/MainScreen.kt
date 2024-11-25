package com.teamoffroad.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.TYPE_ANNOUNCEMENT
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.feature.main.component.CharacterChatAnimation
import com.teamoffroad.feature.main.component.MainBottomBar
import com.teamoffroad.feature.main.component.MainNavHost
import com.teamoffroad.feature.main.component.UserChat
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
    //navigateToCharacterChatScreen: (Int, String) -> Unit,
) {
    val mainContainerSetting = remember { mutableStateOf(false) }
    val isMainUiState by viewModel.mainUiState.collectAsState()
    val characterChatUiState = viewModel.characterChatUiState.collectAsStateWithLifecycle()
    val userChatUiState = viewModel.userChatUiState.collectAsStateWithLifecycle()
    val userChattingText = viewModel.userChattingText.collectAsStateWithLifecycle()

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
        userChattingText = userChattingText,
        modifier = modifier,
        mainContainerSetting = mainContainerSetting,
        characterChatUiState = characterChatUiState,
        userChatUiState = userChatUiState,
        updateAnswerCharacterChatButtonState = viewModel::updateAnswerCharacterChatButtonState,
        updateCharacterChatExist = viewModel::updateCharacterChatExist,
        updateUserWatchingCharacterChat = viewModel::updateUserWatchingCharacterChat,
        updateUserChattingText = viewModel::updateUserChattingText,
        updateShowUserChatTextField = viewModel::updateShowUserChatTextField,
        sendChat = viewModel::sendChat,
        //navigateToCharacterChatScreen = navigateToCharacterChatScreen
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
    //navigateToCharacterChatScreen: (Int, String) -> Unit
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

                    if (characterChatUiState.value.isCharacterChattingExist) {
                        CharacterChatAnimation(
                            characterChatUiState = characterChatUiState,
                            userChatUiState = userChatUiState,
                            updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
                            updateCharacterChatExist = updateCharacterChatExist,
                            updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                            updateShowUserChatTextField = updateShowUserChatTextField,
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 370.dp) // TODO: TextField 위치 조정 필요
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            if (characterChatUiState.value.isAnswerButtonClicked) {
                                UserChat(
                                    characterChatUiState = characterChatUiState,
                                    chattingText = userChattingText,
                                    updateShowUserChatTextField = updateShowUserChatTextField,
                                    updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                                    userChatUiState = userChatUiState,
                                    sendChat = sendChat,
                                    updateUserChattingText = updateUserChattingText
                                )
                            }
                        }

                    }

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
