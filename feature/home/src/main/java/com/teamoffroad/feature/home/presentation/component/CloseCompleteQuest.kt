package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ContentsLocation
import com.teamoffroad.core.designsystem.component.ContentsTitle
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarData
import com.teamoffroad.offroad.feature.home.R

@Composable
fun CloseCompleteRequest(modifier: Modifier = Modifier, data: HomeProgressBarData) {
    Surface(
        color = Contents2,
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                ContentsTitle(title = data.title)
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_home_close_complete),
                    contentDescription = "recent quest",
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            LinearProgressBar(data)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            ContentsLocation(data.location)
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}