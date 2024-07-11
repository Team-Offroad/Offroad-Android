package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.common.component.ContentsLocation
import com.teamoffroad.core.common.component.ContentsTitle
import com.teamoffroad.core.designsystem.theme.Contents1
import com.teamoffroad.core.designsystem.theme.Contents1GraphMain
import com.teamoffroad.core.designsystem.theme.Contents1GraphSub
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.home.HomeProgressBarData
import com.teamoffroad.offroad.feature.home.R

@Composable
fun RecentQuest(modifier: Modifier = Modifier, data: HomeProgressBarData) {
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
            CircleProgressBar()
            Spacer(modifier = Modifier.padding(top = 12.dp))
            ContentsLocation(data.location)
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}

@Composable
private fun CircleProgressBar() {
    var recentQuestProgress by remember { mutableStateOf(0.8f) }

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (main, text) = createRefs()

        CircularProgressIndicator(
            progress = { recentQuestProgress },
            color = Contents1GraphMain,
            trackColor = Contents1GraphSub,
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .size(82.dp)
                .constrainAs(main) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "3/4",
            color = Main1,
            style = OffroadTheme.typography.bothRecentNum,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(main.start)
                    end.linkTo(main.end)
                    top.linkTo(main.top)
                    bottom.linkTo(main.bottom)
                }
        )
    }
}