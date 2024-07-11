package com.teamoffroad.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.home.presentation.component.CharacterImage
import com.teamoffroad.feature.home.presentation.component.CharacterNameText
import com.teamoffroad.feature.home.presentation.component.CloseCompleteRequest
import com.teamoffroad.feature.home.presentation.component.EmblemNameText
import com.teamoffroad.feature.home.presentation.component.HomeIcons
import com.teamoffroad.feature.home.presentation.component.NicknameText
import com.teamoffroad.feature.home.presentation.component.RecentQuest
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R

@Composable
internal fun HomeScreen(
    padding: PaddingValues,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = Main1
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            UsersAdventuresInformation(modifier = Modifier)
            Spacer(modifier = Modifier.padding(top = 12.dp))
            UsersQuestInformation()
        }
    }
}

@Composable
private fun UsersAdventuresInformation(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            NicknameText("비포장도로")
            CharacterNameText("오푸")
        }
        Box {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                HomeBackground()
                HomeIcons()
            }
            CharacterImage()
        }
    }
    Spacer(modifier = Modifier.padding(18.dp))
    EmblemNameText(Modifier)
}

@Composable
private fun HomeBackground() {
    Image(
        painter = painterResource(id = R.drawable.img_home_stamp),
        contentDescription = "stamp",
    )
}

@Composable
private fun UsersQuestInformation() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(start = 24.dp))
        RecentQuest(
            modifier = Modifier.weight(1f),
            data = HomeProgressBarModel(
                stringResource(id = R.string.home_recent_quest_title), 3, 4, "홍대입구 한바퀴"
            )
        )
        Spacer(modifier = Modifier.padding(start = 12.dp))
        CloseCompleteRequest(
            modifier = Modifier.weight(1f),
            data = HomeProgressBarModel(
                stringResource(id = R.string.home_close_complete_quest_title), 7, 8, "도심 속 공원 탐방"
            )
        )
        Spacer(modifier = Modifier.padding(end = 24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OffroadTheme {
        HomeScreen(padding = PaddingValues())
    }
}

