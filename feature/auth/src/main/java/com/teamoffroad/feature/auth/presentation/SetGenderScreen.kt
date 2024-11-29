package com.teamoffroad.feature.auth.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.GenderHintButton
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.model.SetGenderStateResult
import com.teamoffroad.feature.auth.presentation.model.SetGenderUiState
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SetGenderScreen(
    nickname: String,
    birthDate: String?,
    navigateToSetCharacter: () -> Unit,
    viewModel: SetGenderViewModel = hiltViewModel(),
) {
    val isGenderState by viewModel.genderUiState.collectAsState()

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(Main1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OffroadActionBar()
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.auth_skip),
                color = Gray300,
                style = OffroadTheme.typography.hint,
                modifier = Modifier.clickable {
                    viewModel.fetchUserProfile(nickname, birthDate, null)
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
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.auth_set_gender_sub_title),
            color = Main2,
            style = OffroadTheme.typography.subtitleReg,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(vertical = 28.dp))
        SetGenderButton(viewModel, isGenderState)
        Spacer(modifier = Modifier.weight(1f))
        OffroadBasicBtn(
            modifier = Modifier
                .padding(bottom = 72.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.auth_basic_button),
            onClick = { viewModel.fetchUserProfile(nickname, birthDate) },
            isActive = isGenderState.genderResult == SetGenderStateResult.Select,
        )
    }


    if (isGenderState.genderResult == SetGenderStateResult.Success) navigateToSetCharacter()
    else if (isGenderState.genderResult == SetGenderStateResult.Error) {
        Toast.makeText(
            LocalContext.current,
            stringResource(R.string.auth_set_gender_network_error),
            Toast.LENGTH_SHORT
        ).show()
        viewModel.updateGenderEmpty()
    }
}

@Composable
fun SetGenderButton(
    viewModel: SetGenderViewModel,
    isGenderState: SetGenderUiState,
) {
    val (male, female, other) = when (isGenderState.selectedGender) {
        "MALE" -> {
            Triple(true, false, false)
        }

        "FEMALE" -> {
            Triple(false, true, false)
        }

        "OTHER" -> {
            Triple(false, false, true)
        }

        else -> {
            Triple(false, false, false)
        }
    }
    Column {
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickableWithoutRipple {
                    viewModel.updateCheckedGender(
                        "MALE"
                    )
                },
            value = stringResource(R.string.auth_set_gender_male),
            isActive = male
        )
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickableWithoutRipple {
                    viewModel.updateCheckedGender(
                        "FEMALE"
                    )
                },
            value = stringResource(R.string.auth_set_gender_female),
            isActive = female
        )
        GenderHintButton(
            modifier = Modifier
                .clickableWithoutRipple {
                    viewModel.updateCheckedGender(
                        "OTHER"
                    )
                },
            value = stringResource(R.string.auth_set_gender_other),
            isActive = other
        )
    }
}
