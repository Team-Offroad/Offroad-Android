package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.OffroadActionBar
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
    viewModel: SetNicknameViewModel = hiltViewModel(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val isNicknameState by viewModel.nicknameUiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager),
        color = Main1,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OffroadActionBar()
            Text(
                text = "모험가 프로필 작성",
                color = Main2,
                style = OffroadTheme.typography.profileTitle,
                modifier = Modifier.padding(top = 104.dp, bottom = 6.dp)
            )
            Text(
                text = "어떤 이름으로 불러드리면 될까요?",
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
                modifier = Modifier.padding(bottom = 56.dp)
            )
            Column {
                Row {
                    NicknameTextField(
                        value = isNicknameState.nickname,
                        placeholder = "닉네임 입력",
                        onValueChange = {
                            viewModel.updateNicknamesValid(it)
                        },
                        textAlign = Alignment.CenterStart,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 218.dp)
                            .height(43.dp)
                            .focusRequester(focusRequester = focusRequester)
                            .padding(end = 6.dp),
                        nicknameValidateResult = isNicknameState.nicknameValidateResult,
                        interactionSource = interactionSource
                    )
                    OnboardingButton(
                        text = "중복확인",
                        isActive = isNicknameState.nicknameValidateResult,
                        modifier = Modifier
                            .width(82.dp)
                            .height(42.dp),
                        onButtonClick = {
                            viewModel.getDuplicateNickname()
                            focusManager.clearFocus()
                        },
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                NicknameHintText(
                    text = isNicknameState.nickname,
                    isDuplicate = isNicknameState.nicknameValidateResult,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            SetNicknameButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 72.dp)
                    .height(50.dp),
                onClick = {
                    navigateToSetBirthDate(isNicknameState.nickname)
                },
                isActive = isNicknameState.nicknameValidateResult,
                text = "다음"
            )
        }
    }
}
