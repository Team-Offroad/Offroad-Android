package com.teamoffroad.feature.auth.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.teamoffroad.feature.auth.domain.model.UserGender
import com.teamoffroad.feature.auth.presentation.component.GenderHintButton
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
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
    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .clickableWithoutRipple(interactionSource) { viewModel.updateGenderEmpty() },
        color = Main1,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OffroadActionBar()
            Spacer(modifier = Modifier.padding(vertical = 22.dp))
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
                    modifier = Modifier.clickable {
                        viewModel.fetchUserProfile(nickname, birthDate, null)
                    }
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 26.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.auth_on_boarding_title),
                color = Main2,
                style = OffroadTheme.typography.profileTitle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.auth_set_gender_sub_title),
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
            SetGenderButton(viewModel, isGenderState, interactionSource)
            Spacer(modifier = Modifier.weight(1f))
            OffroadBasicBtn(
                modifier = Modifier
                    .width(312.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { viewModel.fetchUserProfile(nickname, birthDate) },
                isActive = isGenderState != SetGenderUiState.Loading,
                text = stringResource(R.string.auth_basic_button),
            )
            Spacer(modifier = Modifier.height(72.dp))
        }
    }

    if (isGenderState == SetGenderUiState.Success) navigateToSetCharacter()
    else if (isGenderState == SetGenderUiState.Error) {Toast.makeText(
        LocalContext.current,
        "네트워크 연결을 확인해 주세요.",
        Toast.LENGTH_SHORT
    ).show()
    viewModel.updateGenderEmpty()}
}

@Composable
fun SetGenderButton(
    viewModel: SetGenderViewModel,
    isGenderState: SetGenderUiState,
    interactionSource: MutableInteractionSource,
) {
    val (male, female, other) = when (isGenderState) {
        SetGenderUiState.Select(UserGender.MALE.name) -> {
            Triple(true, false, false)
        }

        SetGenderUiState.Select(UserGender.FEMALE.name) -> {
            Triple(false, true, false)
        }

        SetGenderUiState.Select(UserGender.OTHER.name) -> {
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
                .clickableWithoutRipple(interactionSource) { viewModel.updateCheckedGender("MALE") },
            value = stringResource(R.string.auth_set_gender_male),
            isActive = male
        )
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickableWithoutRipple(interactionSource) { viewModel.updateCheckedGender("FEMALE") },
            value = stringResource(R.string.auth_set_gender_female),
            isActive = female
        )
        GenderHintButton(
            modifier = Modifier
                .clickableWithoutRipple(interactionSource) { viewModel.updateCheckedGender("OTHER") },
            value = stringResource(R.string.auth_set_gender_other),
            isActive = other
        )
    }
}
