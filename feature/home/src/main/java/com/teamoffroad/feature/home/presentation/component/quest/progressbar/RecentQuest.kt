package com.teamoffroad.feature.home.presentation.component.quest.progressbar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Contents1
import com.teamoffroad.feature.home.presentation.component.quest.ContentsLocation
import com.teamoffroad.feature.home.presentation.component.quest.ContentsTitle
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R

@Composable
fun RecentQuest(modifier: Modifier = Modifier, data: HomeProgressBarModel) {
    Surface(
        color = Contents1,
        modifier = modifier.clip(shape = RoundedCornerShape(10.dp))
    ) {
        Column {
            Row {
                ContentsTitle(data.title)
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_home_recent_quest),
                    contentDescription = "recent quest",
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 14.dp))
            CircleProgressBar(data)
            Spacer(modifier = Modifier.padding(top = 12.dp))
            ContentsLocation(data.location)
            Log.d("offroad data.location", data.location) //
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}

