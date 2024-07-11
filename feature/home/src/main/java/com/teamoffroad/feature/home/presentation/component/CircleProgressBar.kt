package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Contents1GraphMain
import com.teamoffroad.core.designsystem.theme.Contents1GraphSub
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun CircleProgressBar() {
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