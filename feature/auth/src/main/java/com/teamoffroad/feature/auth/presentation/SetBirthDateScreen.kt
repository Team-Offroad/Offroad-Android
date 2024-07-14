package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.NicknameTextField

@Composable
internal fun SetBirthDateScreen(
    navigateToHome: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1
    ) {
        Column {
            Spacer(modifier = Modifier.padding(vertical = 22.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 22.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "건너뛰기",
                    color = Gray300,
                    style = OffroadTheme.typography.hint,
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 26.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "모험가 프로필 작성",
                color = Main2,
                style = OffroadTheme.typography.profileTitle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "나이를 알려주세요.",
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
            Text(
                modifier = Modifier
                    .padding(start = 24.dp),
                text = "생년월일 입력",
                color = Main2,
                fontSize = 16.sp,
                style = OffroadTheme.typography.subtitle2Bold,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NicknameTextField(
                    placeholder = "YYYY",
                    onValueChange = {},
                    textAlign = Alignment.Center,
                    modifier = Modifier
                        .width(84.dp)
                        .height(43.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 8.dp),
                    text = "년",
                    color = Main2,
                    style = OffroadTheme.typography.subtitleReg,
                )
                NicknameTextField(
                    placeholder = "MM",
                    onValueChange = {},
                    textAlign = Alignment.Center,
                    modifier = Modifier
                        .width(66.dp)
                        .height(43.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 8.dp),
                    text = "월",
                    color = Main2,
                    style = OffroadTheme.typography.subtitleReg,
                )
                NicknameTextField(
                    placeholder = "DD",
                    onValueChange = {},
                    textAlign = Alignment.Center,
                    modifier = Modifier
                        .width(66.dp)
                        .height(43.dp)
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 20.dp),
                    text = "일",
                    color = Main2,
                    style = OffroadTheme.typography.subtitleReg,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            OffroadBasicBtn(
                modifier = Modifier
                    .width(312.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = navigateToHome,
                isActive = true,
                text = "다음"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
