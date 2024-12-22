package com.teamoffroad.feature.auth.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SettingCharacter
import com.teamoffroad.core.designsystem.theme.SettingCoupon
import com.teamoffroad.core.designsystem.theme.SettingSetting
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.component.SetCharacterDialog
import com.teamoffroad.feature.auth.presentation.component.SetCharacterIndicator
import com.teamoffroad.feature.auth.presentation.component.ShowSetCharacterPager
import com.teamoffroad.feature.auth.presentation.model.SetCharacterUiState
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SetCharacterScreen(
    nickname: String,
    birthDate: String?,
    gender: String?,
    navigateToSelectedCharacter: (String) -> Unit,
    viewModel: SetCharacterViewModel = hiltViewModel(),
) {
    var showDialog by remember { mutableStateOf(false) }
    val characters by viewModel.characters.collectAsStateWithLifecycle()
    val selectedCharacter by viewModel.selectedCharacter.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
    val backgroundColor = when (selectedCharacter.id) {
        1 -> SettingCharacter
        2 -> SettingSetting
        3 -> SettingCoupon
        else -> White
    }
    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(0.6f)
                .background(backgroundColor),
        ) {
            OffroadActionBar(backgroundColor)
            Text(
                modifier = Modifier
                    .padding(top = 70.dp, bottom = 32.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.auth_character_title),
                color = Main2,
                style = OffroadTheme.typography.title,
            )
            Box(
                modifier = Modifier.weight(1f)
            ) {
                if (characters.isNotEmpty()) {
                    ShowSetCharacterPager(
                        pagerState = pagerState,
                        imageSize = characters.size,
                        characterRes = characters.map { it.characterBaseImageUrl },
                        viewModel::updateSelectedCharacter
                    )
                }
            }
            SetCharacterIndicator(
                Modifier
                    .padding(top = 22.dp, bottom = 32.dp)
                    .align(Alignment.CenterHorizontally),
                characters.size,
                pagerState
            )
        }
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth()
                .background(Main1),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp, bottom = 20.dp),
                text = selectedCharacter.name,
                style = OffroadTheme.typography.title,
            )
            HorizontalDivider(
                color = Gray300.copy(alpha = 0.25f),
                thickness = 1.dp,
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .width(260.dp)
                    .height(1.dp)
                    .background(Main1.copy(alpha = 0.25f))
            )
            Text(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .padding(horizontal = 50.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight()
                    .weight(1f),
                text = selectedCharacter.description,
                style = OffroadTheme.typography.textRegular,
                color = Main2,
                textAlign = TextAlign.Center,
            )
            OffroadBasicBtn(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 72.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.auth_basic_button),
                onClick = { showDialog = true },
                isActive = true,
            )
        }
    }
    if (uiState is SetCharacterUiState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = 0.55f)),
            contentAlignment = Alignment.Center,
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear))
            LottieAnimation(
                modifier = Modifier,
                composition = composition,
            )
        }
    }
    if (showDialog) {
        SetCharacterDialog(
            character = selectedCharacter,
            onDismissRequest = { showDialog = false },
            onConfirm = {
                showDialog = false
                viewModel.fetchUserProfile(
                    nickname = nickname,
                    birthDate = birthDate,
                    gender = gender,
                    characterId = selectedCharacter.id
                )
            }
        )
    }
    if (uiState is SetCharacterUiState.Success) navigateToSelectedCharacter(selectedCharacter.characterBaseImageUrl)
}
