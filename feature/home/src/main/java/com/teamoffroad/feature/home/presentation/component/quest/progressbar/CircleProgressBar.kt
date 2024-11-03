package com.teamoffroad.feature.home.presentation.component.quest.progressbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Contents1GraphMain
import com.teamoffroad.core.designsystem.theme.Contents1GraphSub
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CircleProgressBar(data: HomeProgressBarModel, viewModel: HomeViewModel) {
    val recentQuestProgress = viewModel.circleProgressBar.value

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (main, text) = createRefs()

        CircularProgressIndicator(
            progress = recentQuestProgress,
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
            text = "${data.amount}/${data.total}",
            color = Main1,
            style = OffroadTheme.typography.bothRecentNumBold,
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