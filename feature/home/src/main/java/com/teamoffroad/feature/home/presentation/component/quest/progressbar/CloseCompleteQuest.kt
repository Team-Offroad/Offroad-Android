package com.teamoffroad.feature.home.presentation.component.quest.progressbar

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
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.home.presentation.component.quest.ContentsLocation
import com.teamoffroad.feature.home.presentation.component.quest.ContentsTitle
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R

@Composable
fun CloseCompleteRequest(modifier: Modifier = Modifier, data: HomeProgressBarModel) {
    Surface(
        color = Contents2,
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row {
                ContentsTitle(data.title, Sub4)
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_home_close_complete),
                    contentDescription = "recent quest",
                )
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            LinearProgressBar(data)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            ContentsLocation(data.location, Sub4)
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}