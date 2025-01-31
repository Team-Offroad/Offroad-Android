package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreInfoWindow(
    title: String,
    shortIntroduction: String,
    categoryImage: String,
    address: String,
    visitCount: Int,
    onButtonClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier
                .background(color = Main3, shape = RoundedCornerShape(8.dp))
                .width(230.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_explore_info_window_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .height(84.dp)
                    .offset(x = (-22).dp, y = (-52).dp)
            )
            Column(
                modifier = Modifier
                    .padding(bottom = 14.dp, start = 14.dp, end = 14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(bottom = 10.dp),
                ) {
                    Text(
                        text = title,
                        color = Main2,
                        modifier = Modifier
                            .widthIn(max = 136.dp),
                        style = OffroadTheme.typography.tooltipTitle,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    AdaptationImage(
                        imageUrl = categoryImage,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                            .size(width = 32.dp, height = 18.dp)
                            .padding(start = 4.dp, end = 8.dp)
                            .align(Alignment.Bottom),
                        contentScale = ContentScale.FillWidth,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(
                    text = shortIntroduction,
                    color = Main2,
                    style = OffroadTheme.typography.textContents,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Text(
                    text = address,
                    color = Gray400,
                    style = OffroadTheme.typography.textContentsSmall,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.explore_visit_count, visitCount),
                    overflow = TextOverflow.Ellipsis,
                    style = OffroadTheme.typography.tooltipNumber,
                    color = Main2,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Sub4, shape = RoundedCornerShape(6.dp))
                        .clickable { onButtonClick() },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.explore_explore_button_label),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = White,
                        style = OffroadTheme.typography.btnSmall,
                        modifier = Modifier.padding(vertical = 8.dp),
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_explore_info_window_close),
                contentDescription = "Close",
                modifier = Modifier
                    .size(48.dp)
                    .padding(18.dp)
                    .clickable { onCloseButtonClick() }
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.Fit,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.bg_explore_info_window_arrow),
            contentDescription = "",
            modifier = Modifier.offset(y = (-2).dp),
        )
    }
}
