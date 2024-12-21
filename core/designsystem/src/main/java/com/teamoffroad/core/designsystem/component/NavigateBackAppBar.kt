package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.core.designsystem.R

@Composable
fun NavigateBackAppBar(
    modifier: Modifier = Modifier,
    mainColor: Color = Main2,
    backgroundColor: Color = Main1,
    text: String = "",
    navigateToBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { navigateToBack() })
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_navigate_back),
                contentDescription = null,
                colorFilter = ColorFilter.tint(mainColor),
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(48.dp)
                    .padding(12.dp),
            )
            Text(
                text = text,
                style = OffroadTheme.typography.textRegular,
                color = mainColor,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}