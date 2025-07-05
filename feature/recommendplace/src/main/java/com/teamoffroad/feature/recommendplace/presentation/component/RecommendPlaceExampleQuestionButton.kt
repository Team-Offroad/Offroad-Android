package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Black55
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White75

@Composable
fun RecommendPlaceExampleQuestionButton(
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(top = 14.dp, bottom = 14.dp)
    ) {
        val exampleQuestions = listOf(
            "강남에 가는데\n오늘 날씨에 맞는 식당 추천해줘." to "오늘 날씨에 맞는 식당",
            "기분이 별로야.\n판교에 스트레스 풀릴 음식 없나?" to "판교에 스트레스 풀릴",
            "여의도 점심 메뉴 추천해주라.\n소화 잘 되는 음식이면 좋겠어." to "여의도 점심 메뉴"
        )

        exampleQuestions.forEachIndexed { index, (fullText, boldText) ->
            val startPadding = if (index == 0) 24.dp else 0.dp
            val endPadding = if (index == exampleQuestions.lastIndex) 24.dp else 12.dp

            val annotatedText = buildAnnotatedString {
                pushStyle(SpanStyle(color = Black25))
                val start = fullText.indexOf(boldText)
                if (start >= 0) {
                    append(fullText.substring(0, start))
                    pushStyle(SpanStyle(color = Black55))
                    append(fullText.substring(start, start + boldText.length))
                    pop()
                    append(fullText.substring(start + boldText.length))
                } else {
                    append(fullText)
                }
                pop()
            }

            Text(
                text = annotatedText,
                modifier = Modifier
                    .padding(start = startPadding, end = endPadding)
                    .background(
                        color = White75,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .clickableWithoutRipple { onClick(fullText) },
                style = OffroadTheme.typography.boxMedi.copy(lineHeight = 16.sp)
            )
        }
    }
}