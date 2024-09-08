package com.teamoffroad.feature.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.BirthDateHintText
import com.teamoffroad.feature.auth.presentation.component.BirthDateTextField
import com.teamoffroad.feature.auth.presentation.component.BirthDateValidateResult
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun SetBirthDateScreen(
    nickname: String,
    navigateToSetGender: (String, String?) -> Unit,
    viewModel: SetBirthDateViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current
    val yearFocusRequester = remember { FocusRequester() }
    val monthFocusRequester = remember { FocusRequester() }
    val dayFocusRequester = remember { FocusRequester() }
    val isBirthDateState by viewModel.birthDateUiState.collectAsState()

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(color = Main1),
    ) {
        OffroadActionBar()
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
                modifier = Modifier.clickable {
                    navigateToSetGender(nickname, null)
                }
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
            text = "나이를 알려주세요.",
            color = Main2,
            style = OffroadTheme.typography.subtitleReg,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(vertical = 28.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "생년월일 입력",
                color = Main2,
                fontSize = 16.sp,
                style = OffroadTheme.typography.subtitle2Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.Start)
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                val pattern = remember { Regex("^\\d+\$") }
                BirthDateTextField(
                    value = isBirthDateState.year,
                    placeholder = "YYYY",
                    onValueChange = {
                        if (it.isBlank() || it.matches(pattern)) {
                            viewModel.updateCheckedYear(it)
                            if (it.length == 4) {
                                monthFocusRequester.requestFocus()
                            }
                        }
                    },
                    textAlign = Alignment.Center,
                    maxLength = 4,
                    modifier = Modifier
                        .width(84.dp)
                        .height(43.dp)
                        .focusRequester(yearFocusRequester),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { monthFocusRequester.requestFocus() }
                    ),
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 8.dp),
                    text = "년",
                    color = Main2,
                    style = OffroadTheme.typography.subtitleReg,
                )
                BirthDateTextField(
                    modifier = Modifier
                        .width(66.dp)
                        .height(43.dp)
                        .focusRequester(monthFocusRequester)
                        .onFocusChanged {
                            viewModel.updateMonthLength()
                        },
                    placeholder = "MM",
                    value = isBirthDateState.month,
                    onValueChange = {
                        if (it.isBlank() || it.matches(pattern)) {
                            viewModel.updateCheckedMonth(it)
                            if (it.length == 2) {
                                dayFocusRequester.requestFocus()
                            }
                        }
                    },
                    maxLength = 2,
                    textAlign = Alignment.Center,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { dayFocusRequester.requestFocus() }
                    ),
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 8.dp),
                    text = "월",
                    color = Main2,
                    style = OffroadTheme.typography.subtitleReg,
                )
                BirthDateTextField(
                    modifier = Modifier
                        .width(66.dp)
                        .height(43.dp)
                        .focusRequester(dayFocusRequester)
                        .onFocusChanged {
                            viewModel.updateDayLength()
                        },
                    value = isBirthDateState.day,
                    placeholder = "DD",
                    onValueChange = {
                        if (it.isBlank() || it.matches(pattern)) {
                            viewModel.updateCheckedDate(it)
                            if (it.length == 2) {
                                focusManager.clearFocus()
                            }
                        }
                    },
                    maxLength = 2,
                    textAlign = Alignment.Center,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 20.dp),
                    text = "일",
                    color = Main2,
                    style = OffroadTheme.typography.subtitleReg,
                )
            }
            BirthDateHintText(
                modifier = Modifier
                    .padding(top = 12.dp),
                isVisible = isBirthDateState.birthDateValidateResult
            )
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        OffroadBasicBtn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                val birthDate =
                    if (isBirthDateState.year.isNotEmpty() && isBirthDateState.month.isNotEmpty() && isBirthDateState.day.isNotEmpty()) {
                        "${isBirthDateState.year}-${isBirthDateState.month}-${isBirthDateState.day}"
                    } else {
                        null
                    }
                navigateToSetGender(nickname, birthDate)
            },
            isActive = isBirthDateState.birthDateValidateResult == BirthDateValidateResult.Success,
            text = "다음"
        )
    }
}
