package com.teamoffroad.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.common.component.ContentsLocation
import com.teamoffroad.core.common.component.ContentsTitle
import com.teamoffroad.core.common.component.PopupTagActive
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.CharacterName
import com.teamoffroad.core.designsystem.theme.Contents1
import com.teamoffroad.core.designsystem.theme.Contents1GraphMain
import com.teamoffroad.core.designsystem.theme.Contents1GraphSub
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.OpticianSansRegular
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.component.CharacterImage
import com.teamoffroad.feature.home.presentation.component.CharacterNameText
import com.teamoffroad.feature.home.presentation.component.CloseCompleteRequest
import com.teamoffroad.feature.home.presentation.component.EmblemNameText
import com.teamoffroad.feature.home.presentation.component.HomeIcons
import com.teamoffroad.feature.home.presentation.component.NicknameText
import com.teamoffroad.feature.home.presentation.component.RecentQuest
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
            data = HomeProgressBarData(
                stringResource(id = R.string.recent_quest), stringResource(
                    id = R.string.circle
                ), 3, 4, "홍대입구 한바퀴"
            )
        )
        Spacer(modifier = Modifier.padding(start = 12.dp))
        CloseCompleteRequest(
            modifier = Modifier.weight(1f),
            data = HomeProgressBarData(
                stringResource(id = R.string.close_complete_quest), stringResource(
                    id = R.string.linear
                ), 7, 8, "도심 속 공원 탐방"
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

