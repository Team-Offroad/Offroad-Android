package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun AcquireCharacter(
    navigateToGainedCharacter: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxSize()
            .clickable { navigateToGainedCharacter() },
    ) {
        Box(
            modifier = Modifier.background(Color(0xFFFFF2C1))
        ) {
            Text(
                text = stringResource(id = R.string.acquire_character),
                style = OffroadTheme.typography.textBold,
                color = Sub4,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 46.dp, start = 42.dp, end = 10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_mypage_acquire_character),
                    contentDescription = "acquire character",
                )
            }
        }
    }
}