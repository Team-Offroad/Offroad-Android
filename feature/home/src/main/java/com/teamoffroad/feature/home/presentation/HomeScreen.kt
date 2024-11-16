package com.teamoffroad.feature.home.presentation

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.ErrorNew
import com.teamoffroad.core.designsystem.theme.HomeGradi1
import com.teamoffroad.core.designsystem.theme.HomeGradi2
import com.teamoffroad.core.designsystem.theme.HomeGradi3
import com.teamoffroad.core.designsystem.theme.HomeGradi4
import com.teamoffroad.core.designsystem.theme.HomeGradi5
import com.teamoffroad.core.designsystem.theme.HomeGradi6
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub55
import com.teamoffroad.core.designsystem.theme.White
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

val homeGradientBackground = Brush.verticalGradient(
    colors = listOf(HomeGradi1, HomeGradi2, HomeGradi3, HomeGradi4, HomeGradi5, HomeGradi6)
)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    category: String?,
    completeQuests: List<String> = emptyList(),
    navigateToGainedCharacter: () -> Unit = {},
) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    val isCompleteQuestDialogShown = remember { mutableStateOf(false) }
    val imeHeight = WindowInsets.ime.getBottom(LocalDensity.current)
    val isChatting = remember { mutableStateOf(false) }
    val chattingText = viewModel.chattingText.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateAutoSignIn()
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
                isChatting = isChatting,
                context = context,
                modifier = Modifier
                    .weight(1f)
                    .actionBarPadding(),
                viewModel = viewModel,
                navigateToGainedCharacter = navigateToGainedCharacter,
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            UsersQuestInformation(context, viewModel)

        }

        if (isChatting.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .imePadding()
                    //.padding(bottom = with(LocalDensity.current) { imeHeight.toDp() })
                    .padding(bottom = 196.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            FinishChatting(isChatting)
                        }
                    }

                    HomeChatTextField(
                        text = chattingText.value,
                        isChatting = isChatting,
                        keyboard = true,
                        onValueChange = { text ->
                            viewModel.updateChattingText(text)
                        },
//                        onFocusChange = { isFocused ->
//                            viewModel.updateIsChatting(isFocused)
//                        }
                    )
                }
            }
        }
    }

    if (isCompleteQuestDialogShown.value) {
        CompleteQuestDialog(
            isCompleteQuestDialogShown = isCompleteQuestDialogShown,
            completeQuests = completeQuests,
            onClickCancel = {
                isCompleteQuestDialogShown.value = false
            },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun UsersAdventuresInformation(
    isChatting: MutableState<Boolean>,
    context: Context,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToGainedCharacter: () -> Unit,
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
                navigateToGainedCharacter = navigateToGainedCharacter,
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
fun FinishChatting(
    isChatting: MutableState<Boolean>,
    backgroundColor: Color = Sub55,
    borderColor: Color = Sub
) {
    Text(
        style = OffroadTheme.typography.subtitle2Semibold,
        text = stringResource(id = R.string.home_chat_finish),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(20.dp),
                color = borderColor
            )
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .clickableWithoutRipple {
                isChatting.value = false
            },
        color = White
    )
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
            category = "NONE"
        )
    }
}

