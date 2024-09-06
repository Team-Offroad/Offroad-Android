package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.GestureNavigation
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.component.CharacterFrameItem
import com.teamoffroad.feature.mypage.presentation.component.GainedCharacterHeader
import com.teamoffroad.feature.mypage.presentation.model.CharacterModel
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState.Error
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState.Loading
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState.Success
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun GainedCharacterScreen(
    navigateToMyPage: () -> Unit,
    gainedCharacterViewModel: GainedCharacterViewModel = hiltViewModel(),
) {

    val uiState = gainedCharacterViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        gainedCharacterViewModel.updateCharacters()
    }

    Column(
        modifier = Modifier
            .then(GestureNavigation())
            .background(color = Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_my_page),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToMyPage()
        }
        GainedCharacterHeader()
        GainedCharacterUiStateHandler(uiState)
    }
}

@Composable
private fun GainedCharacterUiStateHandler(uiState: State<GainedCharacterUiState>) {
    when (uiState.value) {
        is Loading -> Unit

        is Error -> Unit

        is Success -> {
            GainedCharacterItems(
                gainedCharacterList = (uiState.value as Success).characters,
            )
        }

    }
}

@Composable
fun GainedCharacterItems(
    gainedCharacterList: List<CharacterModel>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ListBg)
            .padding(horizontal = 14.dp),
        contentPadding = PaddingValues(top = 14.dp)
    ) {
        itemsIndexed(gainedCharacterList.chunked(2)) { _, pair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                pair.forEach { character ->
                    CharacterFrameItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        characterLabel = character.characterName,
                        characterMainColor = character.characterMainColorCode,
                        characterFrameColor = character.characterFrameColorCode,
                        characterThumbnailImageUrl = character.characterThumbnailImageUrl,
                        isGained = character.isGained,
                        isRepresentative = character.isNewGained,
                        isNewGained = character.isNewGained,
                        onClick = {
                            // TODO: 눌림 이벤트 추가
                        }
                    )
                }
                if (pair.size == 1) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun GainedCharacterScreenPreview() {
    OffroadTheme {
        GainedCharacterScreen({})
    }
}
