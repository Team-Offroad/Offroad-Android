package com.teamoffroad.feature.home.presentation

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.home.domain.model.UserQuests
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
) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.updateCategory(if (category.isNullOrEmpty()) "NONE" else category)
        viewModel.getUsersAdventuresInformation(viewModel.category.value)
        viewModel.getUserQuests()
    }
    StaticAnimationWrapper {
        Surface(
            modifier = Modifier
                .padding(bottom = 74.dp)
                .navigationBarsPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = Main1
        ) {
            StaticAnimationWrapper {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OffroadActionBar()
                    UsersAdventuresInformation(
                        context = context,
                        modifier = Modifier.weight(1f),
                        viewModel = viewModel,
                    )
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    UsersQuestInformation(context, viewModel)
                    Spacer(modifier = Modifier.padding(top = 34.dp))
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun UsersAdventuresInformation(
    context: Context,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
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
        modifier = modifier.fillMaxWidth()
    ) {
        val imageUrl = adventuresInformationData?.baseImageUrl ?: "" // TODO: svg & lottie
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            HomeBackground()
            HomeIcons(
                context = context,
                imageUrl = imageUrl
            )
        }

        Column {
            NicknameText(adventuresInformationData?.nickname ?: "")
            CharacterItem().CharacterNameText(adventuresInformationData?.characterName ?: "")
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                //.fillMaxHeight()
                .align(Alignment.BottomCenter)
        ) {
            CharacterItem().CharacterImage(viewModel, context)
        }
    }
    Spacer(modifier = Modifier.padding(10.dp))
    CharacterItem().EmblemNameText(context, Modifier)
}

@Composable
private fun HomeBackground() {
    Image(
        painter = painterResource(id = R.drawable.img_home_stamp),
        contentDescription = "stamp",
        modifier = Modifier.padding(top = 16.dp)
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
    OnBackButtonListener()
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

