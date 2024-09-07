package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailModel
import com.teamoffroad.feature.mypage.presentation.model.CharacterMotionModel
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun CharacterMotionsContainer(
    gainedCharacterList: List<CharacterMotionModel>,
    characterDetail: CharacterDetailModel,
) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
            .background(color = Main1, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
    ) {
        CharacterMotionTitle()
        CharacterMotionItems(gainedCharacterList, characterDetail)
    }
}

@Composable
private fun CharacterMotionTitle() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 24.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = stringResource(R.string.my_page_character_motion),
            style = OffroadTheme.typography.subtitle2Bold,
            color = Main2,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_my_page_character),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(26.dp)
        )
    }
}

@Composable
private fun CharacterMotionItems(
    gainedCharacterList: List<CharacterMotionModel>,
    characterDetail: CharacterDetailModel,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 14.dp, bottom = 100.dp),
    ) {
        val rows = gainedCharacterList.chunked(2)
        rows.forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth()) {
                rowItems.forEach { character ->
                    CharacterFrameItem(
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1f),
                        characterLabel = character.motionName,
                        characterMainColor = characterDetail.characterSubColorCode,
                        characterFrameColor = characterDetail.characterMainColorCode,
                        characterThumbnailImageUrl = character.characterMotionImageUrl,
                        isGained = character.isGained,
                        isLottieImage = true,
                        isNewGained = character.isNewGained,
                    )
                }
                if (rowItems.size < 2) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
