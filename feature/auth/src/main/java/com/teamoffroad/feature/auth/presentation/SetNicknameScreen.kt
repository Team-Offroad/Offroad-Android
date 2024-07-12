package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.NicknameHintText
import com.teamoffroad.feature.auth.presentation.component.NicknameTextField
import com.teamoffroad.feature.auth.presentation.component.OnboardingButton

@Composable
internal fun SetNicknameScreen(
    padding: PaddingValues,
    navigateToSetBirthDate: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(vertical = 60.dp))
            Text(
                text = "모험가 프로필 작성",
                color = Main2,
                style = OffroadTheme.typography.profileTitle,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                text = "어떤 이름으로 불러드리면 될까요?",
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
                modifier = Modifier

            )
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
            Column {
                Row {
                    NicknameTextField(
                        value = "닉네임 입력",
                        onValueChange = {},
                        modifier = Modifier
                            .defaultMinSize(minWidth = 218.dp)
                            .height(43.dp)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                    OnboardingButton(
                        text = "중복확인", isActive = true,
                        modifier = Modifier
                            .width(82.dp)
                            .height(42.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                NicknameHintText(
                    value = "*한글 2~8자, 영어 2~16자 이내로 작성해주세요.",
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 176.dp))
            OffroadBasicBtn(
                modifier = Modifier
                    .width(312.dp)
                    .height(50.dp),
                onClick = navigateToSetBirthDate,
                isActive = true,
                text = "다음"
            )
        }
    }
}