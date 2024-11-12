package com.teamoffroad.feature.auth.presentation

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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.CharacterSelectBg1
import com.teamoffroad.core.designsystem.theme.CharacterSelectBg2
import com.teamoffroad.core.designsystem.theme.CharacterSelectBg3
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.component.SetCharacterDialog
import com.teamoffroad.feature.auth.presentation.component.SetCharacterIndicator
import com.teamoffroad.feature.auth.presentation.component.ShowSetCharacterPager
import com.teamoffroad.feature.auth.presentation.model.SetCharacterUiState
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SetCharacterScreen(
    navigateToSelectedCharacter: (String) -> Unit,
    viewModel: SetCharacterViewModel = hiltViewModel(),
) {
    var showDialog by remember { mutableStateOf(false) }
    val characters by viewModel.characters.collectAsStateWithLifecycle()
    val selectedCharacter by viewModel.selectedCharacter.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
    val backgroundColor = when (selectedCharacter.id) {
        1 -> CharacterSelectBg1
        2 -> CharacterSelectBg2
        3 -> CharacterSelectBg3
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
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight()
                    .weight(1f),
                text = selectedCharacter.description,
                style = OffroadTheme.typography.textRegular,
                color = Main2,
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
    if (showDialog) {
        SetCharacterDialog(
            character = selectedCharacter,
            onDismissRequest = { showDialog = false },
            onConfirm = {
                showDialog = false
                viewModel.updateCharacter(selectedCharacter.id)
            }
        )
    }
    if (uiState is SetCharacterUiState.Success) navigateToSelectedCharacter(selectedCharacter.characterBaseImageUrl)
}
