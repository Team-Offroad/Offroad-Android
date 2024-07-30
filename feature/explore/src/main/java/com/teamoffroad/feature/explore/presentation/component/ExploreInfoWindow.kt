package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
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
        Card(
            modifier = modifier
                .width(230.dp)
                .wrapContentHeight()
                .background(
                    color = Main3,
                    shape = RoundedCornerShape(8.dp),
                ),
        ) {
            Column(
                modifier = Modifier
                    .background(color = Main3)
                    .paint(painterResource(id = R.drawable.bg_explore_info_window))
                    .border(
                        width = 1.dp,
                        color = Main3,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(top = 0.dp, bottom = 14.dp, start = 14.dp, end = 14.dp)
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = title,
                        color = Main2,
                        fontSize = 17.sp,
                        modifier = Modifier.widthIn(max = 122.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = OffroadTheme.typography.tooltipTitle,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(categoryImage)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = "Category Image",
                        modifier = Modifier
                            .size(width = 32.dp, height = 18.dp)
                            .padding(start = 4.dp, end = 8.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_explore_info_window_close),
                        contentDescription = "Close",
                        modifier = Modifier
                            .size(44.dp)
                            .padding(14.dp)
                            .offset(x = 14.dp)
                            .clickable { onCloseButtonClick() },
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = shortIntroduction,
                    color = Main2,
                    style = OffroadTheme.typography.textContents,
                    fontSize = 13.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Text(
                    text = address,
                    color = Gray400,
                    style = OffroadTheme.typography.textContentsSmall,
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.explore_visit_count, visitCount),
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = OffroadTheme.typography.tooltipNumber,
                    color = Main2,
                    modifier = Modifier.padding(vertical = 2.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Sub, shape = RoundedCornerShape(6.dp))
                        .clickable { onButtonClick() },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.explore_explore_button_label),
                        fontSize = 13.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = White,
                        style = OffroadTheme.typography.btnSmall,
                        modifier = Modifier.padding(vertical = 8.dp),
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.bg_explore_info_window_triangle),
            contentDescription = "",
            modifier = Modifier.offset(y = (-2).dp),
        )
    }
}
