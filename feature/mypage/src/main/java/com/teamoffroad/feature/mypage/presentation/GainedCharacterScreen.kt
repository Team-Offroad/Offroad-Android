package com.teamoffroad.feature.mypage.presentation

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.component.CharacterFrameItem
import com.teamoffroad.feature.mypage.presentation.component.GainedCharacterHeader
import com.teamoffroad.feature.mypage.presentation.model.CharacterModel.CharacterThumbnailModel
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun GainedCharacterScreen() {
    Column(
        modifier = Modifier.background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_my_page),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            // TODO: 마이페이지 이동 추가
        }
        GainedCharacterHeader()
        GainedCharacterItems(CharacterThumbnailModel.dummyCharacters)
    }
}

@Composable
fun GainedCharacterItems(
    gainedCharacterList: List<CharacterThumbnailModel>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
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
                        onClick = { Log.e("123123", "눌림!") }
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
        GainedCharacterScreen()
    }
}
