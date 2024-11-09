package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.NicknameHintText
import com.teamoffroad.feature.auth.presentation.component.NicknameTextField
import com.teamoffroad.feature.auth.presentation.component.NicknameValidateResult
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.component.OnboardingButton
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SetNicknameScreen(
    navigateToSetBirthDate: (String) -> Unit,
    viewModel: SetNicknameViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val isNicknameState by viewModel.nicknameUiState.collectAsState()
    val isFocused = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                isFocused.value = false
            }
            .background(Main1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OffroadActionBar()
        Text(
            text = stringResource(R.string.auth_on_boarding_title),
            color = Main2,
            style = OffroadTheme.typography.profileTitle,
            modifier = Modifier.padding(top = 104.dp, bottom = 6.dp)
        )
        Text(
            text = stringResource(R.string.auth_set_nickname_sub_title),
            color = Main2,
            style = OffroadTheme.typography.subtitleReg,
            modifier = Modifier.padding(bottom = 56.dp)
        )
        Column {
            Row {
                NicknameTextField(
                    value = isNicknameState.nickname,
                    placeholder = stringResource(R.string.auth_set_nickname_text_field_hint),
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
                    isFocused = isFocused.value,
                    onFocusChanged = { focused ->
                        isFocused.value = focused
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            isFocused.value = false
                        }
                    ),
                )
                OnboardingButton(
                    text = stringResource(R.string.auth_set_nickname_check_duplication_button),
                    isActive = isNicknameState.nicknameValidateResult,
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .width(82.dp)
                        .height(42.dp),
                    onButtonClick = {
                        viewModel.getDuplicateNickname()
                        focusManager.clearFocus()
                        isFocused.value = false
                    },
                )
            }
            NicknameHintText(
                text = isNicknameState.nickname,
                isDuplicate = isNicknameState.nicknameValidateResult,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        OffroadBasicBtn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp),
            text = stringResource(R.string.auth_basic_button),
            onClick = { navigateToSetBirthDate(isNicknameState.nickname) },
            isActive = isNicknameState.nicknameValidateResult == NicknameValidateResult.Success,
            )
    }
}

