package com.teamoffroad.feature.home.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.home.presentation.component.HomeIcons
import com.teamoffroad.feature.home.presentation.component.character.CharacterItem
import com.teamoffroad.feature.home.presentation.component.quest.progressbar.CloseCompleteRequest
import com.teamoffroad.feature.home.presentation.component.quest.progressbar.RecentQuest
import com.teamoffroad.feature.home.presentation.component.user.NicknameText
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun HomeScreen(
    padding: PaddingValues,
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = Main1
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            UsersAdventuresInformation(context = context, modifier = Modifier)
            Spacer(modifier = Modifier.padding(top = 12.dp))
            UsersQuestInformation()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun UsersAdventuresInformation(
    context: Context,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        val imageUrl =
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA2MThfMTI5%2FMDAxNzE4NzAwMDc3NDU5.aKzepvLVpANcA_ADj_iPwrCReF3JtmKBrUTfuO2i2e8g.gWSssQEsdKMzp2SMcxWte5v9KB-S9VeyZ7TzERECYVEg.JPEG%2FCK_cm26006990.jpg&type=a340"

        Column {
            NicknameText("비포장도로")
            CharacterItem().CharacterNameText("오푸")
        }
        Box {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                HomeBackground()
                HomeIcons(
                    context = context,
                    url = imageUrl
                )
            }
            CharacterItem().CharacterImage()
        }
    }
    Spacer(modifier = Modifier.padding(18.dp))
    CharacterItem().EmblemNameText(Modifier)
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OffroadTheme {
        HomeScreen(
            padding = PaddingValues(),
        )
    }
}
