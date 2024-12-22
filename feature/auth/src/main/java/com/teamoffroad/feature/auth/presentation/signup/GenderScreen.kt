package com.teamoffroad.feature.auth.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.feature.auth.presentation.component.GenderHintButton
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun GenderScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
    updateCheckedGender: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        SetGenderButton(updateCheckedGender, uiState)
    }
}

@Composable
fun SetGenderButton(
    updateCheckedGender: (String) -> Unit,
    isGenderState: SignUpState,
) {
    val (male, female, other) = when (isGenderState.selectedGender) {
        "MALE" -> Triple(true, false, false)

        "FEMALE" -> Triple(false, true, false)

        "OTHER" -> Triple(false, false, true)

        else -> Triple(false, false, false)
    }
    Column {
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickableWithoutRipple {
                    updateCheckedGender("MALE")
                },
            value = stringResource(R.string.auth_set_gender_male),
            isActive = male
        )
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickableWithoutRipple {
                    updateCheckedGender("FEMALE")
                },
            value = stringResource(R.string.auth_set_gender_female),
            isActive = female
        )
        GenderHintButton(
            modifier = Modifier
                .clickableWithoutRipple {
                    updateCheckedGender("OTHER")
                },
            value = stringResource(R.string.auth_set_gender_other),
            isActive = other
        )
    }
}
