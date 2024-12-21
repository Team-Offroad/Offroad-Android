package com.teamoffroad.feature.auth.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.feature.auth.presentation.component.NicknameHintText
import com.teamoffroad.feature.auth.presentation.component.NicknameTextField
import com.teamoffroad.feature.auth.presentation.component.OnboardingButton
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun NicknameScreen(
    focusManager: FocusManager,
    uiState: SignUpState,
    updateNicknamesValid: (String) -> Unit,
    getDuplicateNickname: () -> Unit,
) {
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val isFocused = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .addFocusCleaner(focusManager) {
                isFocused.value = false
            }
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            Row {
                NicknameTextField(
                    value = uiState.nickname,
                    placeholder = stringResource(R.string.auth_set_nickname_text_field_hint),
                    onValueChange = {
                        updateNicknamesValid(it)
                    },
                    textAlign = Alignment.CenterStart,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 218.dp)
                        .height(43.dp)
                        .focusRequester(focusRequester = focusRequester)
                        .padding(end = 6.dp),
                    nicknameValidateResult = uiState.nicknameScreenResult,
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
                    isActive = uiState.nicknameScreenResult,
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .width(82.dp)
                        .height(42.dp),
                    onButtonClick = {
                        getDuplicateNickname()
                        focusManager.clearFocus()
                        isFocused.value = false
                    },
                )
            }
            NicknameHintText(
                text = uiState.nickname,
                isDuplicate = uiState.nicknameScreenResult,
            )
        }
    }

}