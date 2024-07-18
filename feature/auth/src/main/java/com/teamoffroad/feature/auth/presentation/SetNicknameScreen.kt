package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.NicknameHintText
import com.teamoffroad.feature.auth.presentation.component.NicknameTextField
import com.teamoffroad.feature.auth.presentation.component.OnboardingButton
import com.teamoffroad.feature.auth.presentation.component.SetNicknameButton

@Composable
internal fun SetNicknameScreen(
    navigateToSetBirthDate: (String) -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    var text by remember { mutableStateOf("") }
    val isNicknameState by viewModel.isNicknameState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager),
        color = Main1,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(top = 104.dp))
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
                        value = text,
                        placeholder = "닉네임 입력",
                        onValueChange = {
                            text = it
                            viewModel.updateInputNickname(it)
                        },
                        textAlign = Alignment.CenterStart,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 218.dp)
                            .height(43.dp)
                            .focusRequester(focusRequester = focusRequester),
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                    OnboardingButton(
                        text = "중복확인",
                        isActive = checkMainLength(text),
                        modifier = Modifier
                            .width(82.dp)
                            .height(42.dp),
                        nickname = text,
                        onButtonClick = viewModel::getDuplicateNickname,
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                NicknameHintText(
                    text = text,
                    modifier = Modifier,
                    isDuplicate = isNicknameState,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            SetNicknameButton(
                modifier = Modifier
                    .width(312.dp)
                    .height(50.dp),
                onClick = {
                    navigateToSetBirthDate(text)
                    viewModel.updateNicknameValid(text)
                },
                isActive = isNicknameState,
                text = "다음"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

fun checkMainLength(text: String): Boolean {
    val koreanRegex = Regex("^[가-힣]*$")
    val englishRegex = Regex("^[a-zA-Z]*$")

    return when {
        koreanRegex.matches(text) -> text.length in 2..8
        englishRegex.matches(text) -> text.length in 2..16
        else -> false
    }
}