package com.teamoffroad.feature.home.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.characterchat.presentation.MainCharacterChatViewModel
import com.teamoffroad.characterchat.presentation.component.showCharacterChat
import com.teamoffroad.characterchat.presentation.component.showUserChat
import com.teamoffroad.characterchat.presentation.model.CharacterChatLastUnreadUiState
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.presentation.component.CompleteQuestDialog
import com.teamoffroad.feature.home.presentation.component.HomeIcons
import com.teamoffroad.feature.home.presentation.component.UiState
import com.teamoffroad.feature.home.presentation.component.character.CharacterItem
import com.teamoffroad.feature.home.presentation.component.quest.progressbar.CloseCompleteRequest
import com.teamoffroad.feature.home.presentation.component.quest.progressbar.RecentQuest
import com.teamoffroad.feature.home.presentation.component.user.NicknameText
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    category: String?,
    completeQuests: List<String> = emptyList(),
    navigateToGainedCharacter: () -> Unit = {},
    navigateToCharacterChatScreen: (Int, String) -> Unit,
) {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = hiltViewModel()
    val mainViewModel: MainCharacterChatViewModel = hiltViewModel()
    val characterChatUiState = mainViewModel.characterChatUiState.collectAsStateWithLifecycle()
    val userChatUiState = mainViewModel.userChatUiState.collectAsStateWithLifecycle()
    val characterChatLastUnreadUiState = mainViewModel.characterChatLastUnreadUiState.collectAsStateWithLifecycle()
    val isCompleteQuestDialogShown = remember { mutableStateOf(false) }
    val characterName = homeViewModel.characterName.collectAsStateWithLifecycle()
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {}

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_DENIED
        ) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
    LaunchedEffect(Unit) {
        homeViewModel.updateAutoSignIn()
        homeViewModel.updateFcmToken()
        homeViewModel.updateCategory(if (category.isNullOrEmpty()) "NONE" else category)
        homeViewModel.getUsersAdventuresInformation(homeViewModel.category.value)
        homeViewModel.getUserQuests()
        if (completeQuests.isNotEmpty()) isCompleteQuestDialogShown.value = true
        mainViewModel.getCharacterChatLastUnread()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_home_background),
            contentDescription = "home background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 192.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            UsersAdventuresInformation(
                context = context,
                characterName = characterName.value,
                modifier = Modifier
                    .weight(1f)
                    .actionBarPadding(),
                homeViewModel = homeViewModel,
                characterChatLastUnreadUiState = characterChatLastUnreadUiState,
                navigateToGainedCharacter = navigateToGainedCharacter,
                updateShowUserChatTextField = mainViewModel::updateShowUserChatTextField,
                updateCharacterChatExist = mainViewModel::updateCharacterChatExist,
                updateCharacterName = mainViewModel::updateCharacterName,
                updateLastUnreadChatDosAllRead = mainViewModel::updateLastUnreadChatDosAllRead
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            UsersQuestInformation(context, homeViewModel)

        }
    }

    if (isCompleteQuestDialogShown.value) {
        CompleteQuestDialog(
            isCompleteQuestDialogShown = isCompleteQuestDialogShown,
            completeQuests = completeQuests,
            onClickCancel = { isCompleteQuestDialogShown.value = false },
        )
    }

    showCharacterChat(
        characterChatUiState = characterChatUiState,
        userChatUiState = userChatUiState,
        updateAnswerCharacterChatButtonState = mainViewModel::updateAnswerCharacterChatButtonState,
        updateCharacterChatExist = mainViewModel::updateCharacterChatExist,
        updateUserWatchingCharacterChat = mainViewModel::updateUserWatchingCharacterChat,
        updateShowUserChatTextField = mainViewModel::updateShowUserChatTextField,
        navigateToCharacterChatScreen = navigateToCharacterChatScreen
    )

    showUserChat(
        userChatUiState = userChatUiState,
        characterChatUiState = characterChatUiState,
        userChattingText = mainViewModel.userChattingText.collectAsStateWithLifecycle(),
        updateCharacterChatExist = mainViewModel::updateCharacterChatExist,
        updateUserWatchingCharacterChat = mainViewModel::updateUserWatchingCharacterChat,
        updateUserChattingText = mainViewModel::updateUserChattingText,
        updateShowUserChatTextField = mainViewModel::updateShowUserChatTextField,
        sendChat = mainViewModel::sendChat
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun UsersAdventuresInformation(
    context: Context,
    characterName: String,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    characterChatLastUnreadUiState: State<CharacterChatLastUnreadUiState>,
    navigateToGainedCharacter: () -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateCharacterName: (String) -> Unit,
    updateLastUnreadChatDosAllRead: (Boolean) -> Unit,
) {
    val adventuresInformationState =
        homeViewModel.getUsersAdventuresInformationState.collectAsState(initial = UiState.Loading).value

    val adventuresInformationData = when (adventuresInformationState) {
        is UiState.Success -> adventuresInformationState.data
        is UiState.Failure -> {
            Toast.makeText(context, adventuresInformationState.errorMessage, Toast.LENGTH_SHORT)
                .show()
            null
        }

        else -> null
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            HomeIcons(
                context = context,
                imageUrl = adventuresInformationData?.baseImageUrl ?: "",
                characterName = characterName,
                characterChatLastUnreadUiState = characterChatLastUnreadUiState,
                navigateToGainedCharacter = navigateToGainedCharacter,
                updateShowUserChatTextField = updateShowUserChatTextField,
                updateCharacterChatExist = updateCharacterChatExist,
                updateCharacterName = updateCharacterName,
                updateLastUnreadChatDosAllRead = updateLastUnreadChatDosAllRead
            )
        }

        Column {
            NicknameText(adventuresInformationData?.nickname ?: "")
            CharacterItem().CharacterNameText(adventuresInformationData?.characterName ?: "")
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            CharacterItem().CharacterImage(homeViewModel, context)
        }
    }
    Spacer(modifier = Modifier.padding(10.dp))
    CharacterItem().EmblemNameText(context, Modifier)
}

@Composable
private fun UsersQuestInformation(
    context: Context,
    viewModel: HomeViewModel,
) {
    val userQuestsState =
        viewModel.getUserQuestsState.collectAsState(initial = UiState.Loading).value

    val userQuests = when (userQuestsState) {
        is UiState.Success -> {
            userQuestsState.data
        }

        is UiState.Failure -> {
            Toast.makeText(context, userQuestsState.errorMessage, Toast.LENGTH_SHORT).show()
            null
        }

        else -> null
    }

    val recentQuest = userQuests?.userRecent ?: UserQuests.UserRecent()
    val almostQuest = userQuests?.userAlmost ?: UserQuests.UserAlmost()

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(start = 24.dp))
        RecentQuest(
            modifier = Modifier.weight(1f),
            data = HomeProgressBarModel(
                stringResource(id = R.string.home_recent_quest_title),
                recentQuest.progress,
                recentQuest.completeCondition,
                recentQuest.questName
            ),
            viewModel
        )
        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
        CloseCompleteRequest(
            modifier = Modifier.weight(1f),
            data = HomeProgressBarModel(
                stringResource(id = R.string.home_close_complete_quest_title),
                almostQuest.progress,
                almostQuest.completeCondition,
                almostQuest.questName
            ),
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.padding(end = 24.dp))
    }
}