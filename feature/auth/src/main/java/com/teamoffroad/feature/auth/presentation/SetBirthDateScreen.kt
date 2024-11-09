package com.teamoffroad.feature.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.BirthDateHintText
import com.teamoffroad.feature.auth.presentation.component.BirthDateTextField
import com.teamoffroad.feature.auth.presentation.component.BirthDateValidateResult
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.offroad.feature.auth.R

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
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 22.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.auth_skip),
                color = Gray300,
                style = OffroadTheme.typography.hint,
                modifier = Modifier
                    .clickableWithoutRipple {
                        navigateToSetGender(nickname, null)
                    }
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 6.dp),
            text = stringResource(R.string.auth_on_boarding_title),
            color = Main2,
            style = OffroadTheme.typography.profileTitle,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 56.dp),
            text = stringResource(R.string.auth_set_birth_date_sub_title),
            color = Main2,
            style = OffroadTheme.typography.subtitleReg,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.auth_set_birth_date_text_field_hint),
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
                    modifier = Modifier
                        .width(84.dp)
                        .height(43.dp)
                        .focusRequester(yearFocusRequester),
                    value = isBirthDateState.year,
                    placeholder = stringResource(R.string.auth_set_birth_date_text_field_year_hint),
                    onValueChange = {
                        if (it.isBlank() || it.matches(pattern)) {
                            viewModel.updateCheckedYear(it)
                            it.takeIf { it.length == 4 }?.let {
                                monthFocusRequester.requestFocus()
                            }
                        }
                    },
                    maxLength = 4,
                    isError = isBirthDateState.birthDateValidateResult == BirthDateValidateResult.YearError,
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
                    text = stringResource(R.string.auth_set_birth_date_text_field_year_text),
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
                    placeholder = stringResource(R.string.auth_set_birth_date_text_field_month_hint),
                    value = isBirthDateState.month,
                    onValueChange = {
                        if (it.isBlank() || it.matches(pattern)) {
                            viewModel.updateCheckedMonth(it)
                            it.takeIf { it.length == 2 }?.let {
                                dayFocusRequester.requestFocus()
                            }
                        }
                    },
                    maxLength = 2,
                    isError = isBirthDateState.birthDateValidateResult == BirthDateValidateResult.MonthError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { dayFocusRequester.requestFocus() },
                    ),
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp, end = 8.dp),
                    text = stringResource(R.string.auth_set_birth_date_text_field_month_text),
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
                    placeholder = stringResource(R.string.auth_set_birth_date_text_field_date_hint),
                    onValueChange = {
                        if (it.isBlank() || it.matches(pattern)) {
                            viewModel.updateCheckedDate(it)
                            it.takeIf { it.length == 2 }?.let {
                                focusManager.clearFocus()
                            }
                        }
                    },
                    maxLength = 2,
                    isError = isBirthDateState.birthDateValidateResult == BirthDateValidateResult.DayError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Default,
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 20.dp),
                    text = stringResource(R.string.auth_set_birth_date_text_field_date_text),
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
                val birthDate = when {
                    isBirthDateState.year.isNotEmpty() &&
                            isBirthDateState.month.isNotEmpty() &&
                            isBirthDateState.day.isNotEmpty() -> {
                        "${isBirthDateState.year}-${isBirthDateState.month}-${isBirthDateState.day}"
                    }

                    else -> null
                }
                navigateToSetGender(nickname, birthDate)
            },
            isActive = isBirthDateState.birthDateValidateResult == BirthDateValidateResult.Success,
            text = stringResource(R.string.auth_basic_button),
        )
    }
}
