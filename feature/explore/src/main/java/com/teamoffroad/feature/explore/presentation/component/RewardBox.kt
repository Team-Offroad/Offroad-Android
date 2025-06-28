package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun RewardBox(
    reward: String,
    isComplete: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth()
                    .background(if (isComplete) Main2 else Black15, shape = RoundedCornerShape(6.dp))
                    .padding(vertical = 16.dp),
        ) {
            Text(
                text =
                    if (isComplete) {
                        stringResource(R.string.explore_course_quest_get_reward)
                    } else {
                        stringResource(
                            R.string.explore_course_quest_reward_info,
                            reward,
                        )
                    },
                style = if (isComplete) OffroadTheme.typography.textRegular else OffroadTheme.typography.textBold,
                color = White,
            )
        }
    }
}
