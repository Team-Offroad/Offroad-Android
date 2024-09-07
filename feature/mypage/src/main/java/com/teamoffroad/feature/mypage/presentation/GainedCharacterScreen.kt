package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.GestureNavigation
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
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
    navigateToCharacterDetail: (Int, Boolean) -> Unit,
    navigateToMyPage: () -> Unit,
    gainedCharacterViewModel: GainedCharacterViewModel = hiltViewModel(),
) {

    val uiState = gainedCharacterViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        gainedCharacterViewModel.updateCharacters()
    }

    StaticAnimationWrapper {
        Column(
            modifier = Modifier
                .then(GestureNavigation())
                .fillMaxSize()
                .background(color = Main1)
        ) {
            OffroadActionBar()
            NavigateBackAppBar(
                text = stringResource(R.string.my_page_my_page),
                modifier = Modifier.padding(top = 20.dp),
            ) {
                navigateToMyPage()
            }
            GainedCharacterHeader()
            GainedCharacterUiStateHandler(
                uiState = uiState,
                navigateToCharacterDetail = navigateToCharacterDetail,
            )
        }
    }
}

@Composable
private fun GainedCharacterUiStateHandler(
    uiState: State<GainedCharacterUiState>,
    navigateToCharacterDetail: (Int, Boolean) -> Unit,
) {
    when (uiState.value) {
        is Loading -> Unit

        is Error -> Unit

        is Success -> {
            GainedCharacterItems(
                gainedCharacterList = (uiState.value as Success).characters,
                navigateToCharacterDetail = navigateToCharacterDetail,
            )
        }
    }
}

@Composable
fun GainedCharacterItems(
    gainedCharacterList: List<CharacterModel>,
    navigateToCharacterDetail: (Int, Boolean) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(color = ListBg)
            .padding(horizontal = 14.dp),
        contentPadding = PaddingValues(top = 14.dp),
    ) {
        items(gainedCharacterList) { character ->
            CharacterFrameItem(
                modifier = Modifier
                    .padding(10.dp),
                characterLabel = character.characterName,
                characterMainColor = character.characterSubColorCode,
                characterFrameColor = character.characterMainColorCode,
                characterThumbnailImageUrl = character.characterThumbnailImageUrl,
                isGained = character.isGained,
                isRepresentative = character.isRepresentative,
                isNewGained = character.isNewGained,
                onClick = {
                    if (character.isGained) {
                        navigateToCharacterDetail(character.characterId, character.isRepresentative)
                    }
                },
            )
        }
    }
}
