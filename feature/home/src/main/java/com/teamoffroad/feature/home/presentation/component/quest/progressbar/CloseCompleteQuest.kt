package com.teamoffroad.feature.home.presentation.component.quest.progressbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.component.quest.ContentsContainer
import com.teamoffroad.feature.home.presentation.component.quest.ContentsTitle
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel
import com.teamoffroad.offroad.feature.home.R

@Composable
fun CloseCompleteRequest(
    modifier: Modifier = Modifier,
    data: HomeProgressBarModel,
    viewModel: HomeViewModel,
) {
    viewModel.updateLinearProgressBar(data.amount.toFloat(), data.total.toFloat())

    Surface(
        color = Contents2,
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Spacer(modifier = Modifier.padding(9.dp))
            Row {
                ContentsTitle(data.title.ifEmpty { stringResource(id = R.string.home_quest_default_name) })
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_home_close_complete),
                    contentDescription = "recent quest",
                )
            }
            Box(contentAlignment = Alignment.Center) {
                LinearProgressBar(data, viewModel)
            }
            ContentsContainer(
                modifier = Modifier
                    .padding(top = 18.dp),
                location = data.location,
            )
        }
    }
}