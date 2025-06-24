package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4_80
import com.teamoffroad.offroad.feature.explore.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val DEADLINE_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd")

@Composable
fun DeadlineHeader(
    deadline: LocalDateTime,
    dDay: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .background(Main1)
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp, end = 28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = "퀘스트 마감일 : ${deadline.format(DEADLINE_FORMATTER)}",
            color = Gray400,
            style = OffroadTheme.typography.hint,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(painter = painterResource(R.drawable.ic_quest_calendar_check), contentDescription = null, modifier = Modifier.size(22.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = dDay, color = Sub4_80, style = OffroadTheme.typography.questCompleted)
    }
}

@Preview
@Composable
fun DeadlineHeaderPreview() {
    OffroadTheme {
        DeadlineHeader(deadline = LocalDateTime.now(), dDay = "D-3")
    }
}
