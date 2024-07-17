package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.clickable
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
import com.teamoffroad.core.designsystem.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.auth.presentation.component.GenderHintButton

@Composable
internal fun SetGenderScreen(
    navigateToSetCharacter: () -> Unit,
    viewModel: OnboardingViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "성별을 선택해 주세요.",
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
            SetGenderButton(viewModel)
            Spacer(modifier = Modifier.weight(1f))
            OffroadBasicBtn(
                modifier = Modifier
                    .width(312.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = navigateToSetCharacter,
                isActive = true,
                text = "다음"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SetGenderButton(viewModel: OnboardingViewModel) {

    val (male, female, other) = if (true) {
        Triple(Black, White, Black)
    } else {
        Triple(Gray100, Gray300, White)
    }

    Column {
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickable(enabled = true,
                    onClick = { viewModel.updateCheckedGender("남성") }),
            value = "남성",
            isActive = false
        )
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickable(enabled = true,
                    onClick = { viewModel.updateCheckedGender("여성") }),
            value = "여성",
            isActive = false
        )
        GenderHintButton(
            modifier = Modifier
                .clickable(enabled = true,
                    onClick = { viewModel.updateCheckedGender("기타") }),
            value = "기타",
            isActive = false
        )
    }
}