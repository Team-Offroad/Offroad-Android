package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun UpdateRepresentativeCharacterButton(
    updateRepresentativeCharacter: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .padding(horizontal = 24.dp)
            .background(color = Main1, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 12.dp)
            .clickable {
                updateRepresentativeCharacter()
            },
    ) {
        Text(
            text = stringResource(R.string.my_page_update_representative_character),
            style = OffroadTheme.typography.textContents,
            color = Main1,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
