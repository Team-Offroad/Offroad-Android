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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun CustomInfoWindow(
    title: String,
    shortIntroduction: String,
    address: String,
    visitCount: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    categoryImage: String,
) {
    Image(
        painter = painterResource(id = R.drawable.bg_explore_info_window),
        contentDescription = "Category Image",
        modifier = Modifier
            .width(230.dp)
            .height(182.dp),
        contentScale = ContentScale.Crop,
    )
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(250.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(4.dp))
                AsyncImage(
                    model = categoryImage,
                    contentDescription = "Category Image",
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = shortIntroduction,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp),
            )
            Text(
                text = address,
                fontSize = 12.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 4.dp),
            )
            Text(
                text = "탐험횟수: $visitCount",
                fontSize = 12.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 4.dp),
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "탐험하기",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .background(Color(0xFFFFA726))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { onButtonClick() },
                )
            }
        }
    }
}