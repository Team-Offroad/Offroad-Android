package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreRefreshButton(
    modifier: Modifier,
    text: String,
    radius: Int = 6,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(126.dp)
            .height(32.dp)
            .border(1.dp, Gray100, shape = RoundedCornerShape(radius.dp))
            .shadow(4.dp, shape = RoundedCornerShape(radius.dp))
            .background(color = White, shape = RoundedCornerShape(radius.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_explore_refresh),
                contentDescription = text,
                modifier = Modifier.size(22.dp),
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = text,
                color = Gray400,
                style = OffroadTheme.typography.hint,
            )
        }
    }
}