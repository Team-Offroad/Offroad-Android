package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Gray200
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun EmblemContainer(
    title: String,
    subTitle: String,
    isNew: Boolean,
    isLock: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .background(if (isLock) Gray300 else Main3, shape = RoundedCornerShape(5.dp))
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 16.dp, horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                style = OffroadTheme.typography.textBold,
                color = Sub4,
                textAlign = TextAlign.Start
            )
            Row(modifier = Modifier.align(Alignment.Start)) {
                Text(
                    text = subTitle,
                    style = OffroadTheme.typography.textContents,
                    color = Gray400,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "달성 시 획득",
                    style = OffroadTheme.typography.hint,
                    color = Gray400,
                )
            }

        }
        if (isNew) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 18.dp),
                painter = painterResource(id = R.drawable.ic_my_page_emblems_new),
                contentDescription = "new"
            )
        }
        if (isLock) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_my_page_emblems_lock),
                contentDescription = "lock"
            )
        }
    }
}