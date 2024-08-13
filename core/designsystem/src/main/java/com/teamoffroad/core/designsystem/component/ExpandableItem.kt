package com.teamoffroad.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.offroad.core.designsystem.R

@Composable
fun ExpandableItem(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onExpandClick: () -> Unit,
    defaultContent: @Composable (Boolean) -> Unit,
    extraContent: @Composable () -> Unit = {},
    backgroundColor: Color = Main3,
    cornerRadius: Int = 4,
    verticalPadding: Int = 16,
    horizontalPadding: Int = 18,
) {
    val rotationAngle by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .wrapContentHeight()
            .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius.dp))
            .padding(vertical = verticalPadding.dp, horizontal = horizontalPadding.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                defaultContent(isExpanded)
            }
            Image(
                painter = painterResource(id = R.drawable.ic_expand),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .offset(x = 12.dp)
                    .rotate(rotationAngle)
                    .clickableWithoutRipple { onExpandClick() },
                alignment = Alignment.CenterEnd
            )
        }
        AnimatedVisibility(visible = isExpanded) {
            extraContent()
        }
    }
}