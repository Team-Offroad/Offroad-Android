package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun SettingContainer(
    color: Color,
    text: String,
    isImportant: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = color)
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, // 상호작용 소스
                indication = rememberRipple(
                    color = Gray300,
                    bounded = true
                ),
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                if (isImportant) {
                    Text(
                        text = "[중요] ",
                        style = OffroadTheme.typography.tabBarMedi,
                        color = Sub2
                    )
                }
                Text(
                    text = text,
                    style = OffroadTheme.typography.tabBarMedi,
                    color = Main2
                )
            }
            Image(
                painterResource(id = R.drawable.ic_setting_next),
                contentDescription = "next",
            )
        }
    }
}