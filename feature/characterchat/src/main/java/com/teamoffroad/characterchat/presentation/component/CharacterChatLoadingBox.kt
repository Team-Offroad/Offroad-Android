package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.TimeType
import com.teamoffroad.characterchat.presentation.model.TimeType.AM
import com.teamoffroad.core.designsystem.component.LinearLoadingAnimation
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4

@Composable
fun CharacterChatLoadingBox(
    name: String = "",
    time: Triple<TimeType, Int, Int> = Triple(AM, 0, 0),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth(),
            contentAlignment = Alignment.CenterStart,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(1.dp, BtnInactive, shape = RoundedCornerShape(12.dp))
                    .background(color = Main3, shape = RoundedCornerShape(12.dp))
                    .padding(start = 18.dp),
            ) {
                Text(
                    text = name,
                    style = OffroadTheme.typography.textBold,
                    color = Sub4,
                    modifier = Modifier.padding(vertical = 12.dp),
                )
                Text(
                    text = ": ",
                    color = Main2,
                    style = OffroadTheme.typography.textRegular,
                    modifier = Modifier.padding(start = 4.dp, end = 72.dp),
                )
            }
            LinearLoadingAnimation(
                isLoading = true,
                modifier = Modifier
                    .height(18.dp)
                    .width(80.dp)
                    .padding(bottom = 2.dp)
                    .align(Alignment.CenterEnd),
            )
        }
        TimeLabel(
            modifier = Modifier.align(Alignment.Bottom),
            time = time,
        )
    }
}
