package com.teamoffroad.feature.home.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.HomeGradi1
import com.teamoffroad.core.designsystem.theme.HomeGradi2
import com.teamoffroad.core.designsystem.theme.HomeGradi3
import com.teamoffroad.core.designsystem.theme.HomeGradi4
import com.teamoffroad.core.designsystem.theme.HomeGradi5
import com.teamoffroad.core.designsystem.theme.HomeGradi6
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.core.designsystem.theme.Sub55
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.presentation.component.CompleteQuestDialog
import com.teamoffroad.feature.home.presentation.component.HomeIcons
import com.teamoffroad.feature.home.presentation.component.UiState
import com.teamoffroad.feature.home.presentation.component.character.CharacterChat
import com.teamoffroad.feature.home.presentation.component.character.CharacterChatAnimation
import com.teamoffroad.feature.home.presentation.component.character.CharacterItem
import com.teamoffroad.feature.home.presentation.component.quest.progressbar.CloseCompleteRequest
import com.teamoffroad.feature.home.presentation.component.quest.progressbar.RecentQuest
import com.teamoffroad.feature.home.presentation.component.user.NicknameText
import com.teamoffroad.feature.home.presentation.component.user.UserChat
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.launch

val homeGradientBackground = Brush.verticalGradient(
    colors = listOf(HomeGradi1, HomeGradi2, HomeGradi3, HomeGradi4, HomeGradi5, HomeGradi6)
)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    category: String?,
    completeQuests: List<String> = emptyList(),
    navigateToGainedCharacter: () -> Unit = {},
    navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    val isCompleteQuestDialogShown = remember { mutableStateOf(false) }
    val isUserChatting = remember { mutableStateOf(false) }
    val userChattingText = viewModel.chattingText.collectAsStateWithLifecycle()
    val userSendMessage = remember { mutableStateOf("") }
    val characterChat = viewModel.getCharacterChat.collectAsStateWithLifecycle()
    val isCharacterChatting = viewModel.isCharacterChatting.collectAsStateWithLifecycle()
    val isCharacterChattingLoading =
        viewModel.isCharacterChattingLoading.collectAsStateWithLifecycle()
    val userSendChat = remember { mutableStateOf(false) }
    val characterName = viewModel.characterName.collectAsStateWithLifecycle()
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
        viewModel.updateAutoSignIn()
        viewModel.updateFcmToken()
        viewModel.updateCategory(if (category.isNullOrEmpty()) "NONE" else category)
        viewModel.getUsersAdventuresInformation(viewModel.category.value)
        viewModel.getUserQuests()
        if (completeQuests.isNotEmpty()) isCompleteQuestDialogShown.value = true
    }

    Box(
        modifier = Modifier
            .background(homeGradientBackground)
            .fillMaxSize()
            .padding(bottom = 180.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            UsersAdventuresInformation(
                isChatting = isUserChatting,
                context = context,
                characterName = characterName.value,
                modifier = Modifier
                    .weight(1f)
                    .actionBarPadding(),
                viewModel = viewModel,
                navigateToGainedCharacter = navigateToGainedCharacter,
                navigateToCharacterChatScreen = navigateToCharacterChatScreen
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            UsersQuestInformation(context, viewModel)

        }

        if (isUserChatting.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 196.dp)
            ) {
                UserChat(
                    isChatting = isUserChatting,
                    chattingText = userChattingText,
                    sendMessage = userSendMessage,
                    userSendChat = userSendChat,
                    updateCharacterChatting = viewModel::updateCharacterChatting,
                    updateChattingText = viewModel::updateChattingText,
                    sendChat = viewModel::sendChat
                )
            }
        }


        if (isCharacterChatting.value) {
            CharacterChatAnimation(
                isCharacterChatting = isCharacterChatting,
                isChatting = isUserChatting,
                isCharacterChattingLoading = isCharacterChattingLoading,
                answerCharacterChat = userSendChat,
                characterName = characterName.value,
                characterContent = characterChat.value.characterContent,
                updateCharacterChatting = viewModel::updateCharacterChatting,
                navigateToCharacterChatScreen = navigateToCharacterChatScreen
            )
        }
    }

    if (isCompleteQuestDialogShown.value) {
        CompleteQuestDialog(
            isCompleteQuestDialogShown = isCompleteQuestDialogShown,
            completeQuests = completeQuests,
            onClickCancel = { isCompleteQuestDialogShown.value = false },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun UsersAdventuresInformation(
    isChatting: MutableState<Boolean>,
    context: Context,
    characterName: String,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToGainedCharacter: () -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    val adventuresInformationState =
        viewModel.getUsersAdventuresInformationState.collectAsState(initial = UiState.Loading).value

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
        val imageUrl = adventuresInformationData?.baseImageUrl ?: "" // TODO: svg & lottie
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            HomeIcons(
                isChatting = isChatting,
                context = context,
                imageUrl = imageUrl,
                characterName = characterName,
                navigateToGainedCharacter = navigateToGainedCharacter,
                navigateToCharacterChatScreen = navigateToCharacterChatScreen
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
            CharacterItem().CharacterImage(viewModel, context)
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OffroadTheme {
        HomeScreen(
            //padding = PaddingValues(),
            category = "NONE",
            navigateToCharacterChatScreen = { _, _ -> }
        )
    }
}

