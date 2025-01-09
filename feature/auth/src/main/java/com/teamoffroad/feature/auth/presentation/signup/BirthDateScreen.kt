package com.teamoffroad.feature.auth.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.BirthDateHintText
import com.teamoffroad.feature.auth.presentation.component.BirthDateTextField
import com.teamoffroad.feature.auth.presentation.model.BirthDateFocus
import com.teamoffroad.feature.auth.presentation.model.DateValidateResult
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun BirthDateScreen(
    focusManager: FocusManager,
    uiState: SignUpState,
    updateYear: (String) -> Unit,
    updateMonth: (String) -> Unit,
    updateDate: (String) -> Unit,
    updateMonthLength: () -> Unit,
    updateDateLength: () -> Unit,
    updateFocus: (BirthDateFocus) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .background(color = Main1)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.auth_set_birth_date_text_field_hint),
            color = Main2,
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
                    .weight(1.4f)
                    .height(43.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { updateFocus(BirthDateFocus.YEAR) },
                value = uiState.year,
                placeholder = stringResource(R.string.auth_set_birth_date_text_field_year_hint),
                onValueChange = {
                    if (it.isBlank() || it.matches(pattern)) {
                        updateYear(it)
                    }
                },
                innerPadding = 0.3f,
                maxLength = 4,
                isError = uiState.yearValidateResult == DateValidateResult.Error,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.NumberPassword
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
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
                    .weight(1f)
                    .height(43.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        updateMonthLength()
                        updateFocus(BirthDateFocus.MONTH)
                    },
                placeholder = stringResource(R.string.auth_set_birth_date_text_field_month_hint),
                value = uiState.month,
                onValueChange = {
                    if (it.isBlank() || it.matches(pattern)) {
                        updateMonth(it)
                    }
                },
                innerPadding = 0.34f,
                maxLength = 2,
                isError = uiState.monthValidateResult == DateValidateResult.Error,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.NumberPassword
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
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
                    .weight(1f)
                    .height(43.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        updateDateLength()
                        updateFocus(BirthDateFocus.DAY)
                    },
                value = uiState.day,
                placeholder = stringResource(R.string.auth_set_birth_date_text_field_date_hint),
                onValueChange = {
                    if (it.isBlank() || it.matches(pattern)) {
                        updateDate(it)
                    }
                },
                innerPadding = 0.34f,
                maxLength = 2,
                isError = uiState.dayValidateResult == DateValidateResult.Error,
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
                text = stringResource(R.string.auth_set_birth_date_text_field_date_text),
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
            )
        }
        BirthDateHintText(
            modifier = Modifier
                .padding(top = 12.dp),
            isVisible = uiState.yearValidateResult == DateValidateResult.Error ||
                    uiState.monthValidateResult == DateValidateResult.Error ||
                    uiState.dayValidateResult == DateValidateResult.Error
        )
    }
}
