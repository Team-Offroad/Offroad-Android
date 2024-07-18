package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.theme.Brown
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.auth.presentation.component.SetCharacterDialog
import com.teamoffroad.feature.auth.presentation.component.ShowSetCharacterPager
import com.teamoffroad.feature.auth.presentation.model.SetCharacterUiState

@Composable
internal fun SetCharacterScreen(
    navigateToSelectedCharacter: (String) -> Unit,
    viewModel: SetCharacterViewModel = hiltViewModel(),
) {

    var showDialog by remember { mutableStateOf(false) }
    val characters by viewModel.characters.collectAsStateWithLifecycle()
    val selectedCharacter by viewModel.selectedCharacter.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val backgroundColor = when (selectedCharacter.id) {
        1 -> Color(0xFFFFF4CC)
        2 -> Color(0xFFFFE1C5)
        3 -> Color(0xFFF9E5D2)
        else -> White
    }

    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            OffroadActionBar(backgroundColor)
            Column(
                modifier = Modifier
                    .background(backgroundColor),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 104.dp, bottom = 30.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    text = "동료 캐릭터 선택",
                    color = Main2,
                    style = OffroadTheme.typography.title,
                )
                if (characters.isNotEmpty()) {
                    ShowSetCharacterPager(
                        imageSize = characters.size,
                        characterRes = characters.map { it.characterBaseImageUrl },
                        viewModel::updateSelectedCharacter
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 14.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Main1),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = selectedCharacter.name,
                        style = OffroadTheme.typography.title,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(
                        modifier = Modifier
                            .width(260.dp)
                            .height(1.dp)
                            .background(Brown.copy(alpha = 0.25f))
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedCharacter.description,
                        style = OffroadTheme.typography.textRegular,
                        color = Main2,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxHeight()
                            .weight(1f),
                    )
                }
                OffroadBasicBtn(
                    modifier = Modifier
                        .width(312.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = { showDialog = true },
                    isActive = true,
                    text = "선택",
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
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
