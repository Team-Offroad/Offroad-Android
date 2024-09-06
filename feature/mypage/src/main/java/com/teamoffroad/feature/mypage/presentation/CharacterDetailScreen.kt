package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.feature.mypage.presentation.component.CharacterDescriptionContainer
import com.teamoffroad.feature.mypage.presentation.component.CharacterDetailImageItem
import com.teamoffroad.feature.mypage.presentation.component.CharacterMotionsContainer
import com.teamoffroad.feature.mypage.presentation.component.RepresentativeUpdateResultSnackBar
import com.teamoffroad.feature.mypage.presentation.component.UpdateRepresentativeCharacterButton
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    isRepresentative: Boolean,
    navigateToBack: () -> Unit,
    characterDetailViewModel: CharacterDetailViewModel = hiltViewModel(),
) {
    val uiState = characterDetailViewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        characterDetailViewModel.updateCharacterDetail(characterId, isRepresentative)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(uiState.value.characterDetailModel.characterSubColorCode)),
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_gained_character),
            backgroundColor = Color(uiState.value.characterDetailModel.characterSubColorCode),
            modifier = Modifier.padding(top = 20.dp),
        ) {
            navigateToBack()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            CharacterDetailImageItem(uiState.value)
            CharacterDescriptionContainer(uiState.value)
            if (!isRepresentative) UpdateRepresentativeCharacterButton(characterDetailViewModel::updateIsRepresentative)
            CharacterMotionsContainer(uiState.value.characterMotions, uiState.value.characterDetailModel)
        }
    }

    if (uiState.value.isRepresentativeUpdateSuccess) {
        RepresentativeUpdateResultSnackBar(
            modifier = Modifier.padding(top = 112.dp),
            characterName = uiState.value.characterDetailModel.characterName,
        )
    }
}
