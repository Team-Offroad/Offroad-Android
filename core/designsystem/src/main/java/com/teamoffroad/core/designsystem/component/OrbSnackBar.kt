package com.teamoffroad.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.common.util.applyBold
import com.teamoffroad.core.designsystem.theme.Black55
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.core.designsystem.R

const val SHOWN_SNACK_BAR_DURATION: Long = 3000L

@Composable
fun OrbSnackBar(
    isVisible: Boolean,
    text: String,
    painter: Painter = painterResource(id = R.drawable.ic_success),
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter =
            slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 500),
            ) + fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500)),
        modifier = modifier,
    ) {
        Box(
            modifier =
                Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .background(color = Black55, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                Text(
                    text = text.applyBold(),
                    color = Color.White,
                    style = OffroadTheme.typography.textRegular,
                    modifier = Modifier.padding(start = 10.dp),
                )
            }
        }
    }
}
