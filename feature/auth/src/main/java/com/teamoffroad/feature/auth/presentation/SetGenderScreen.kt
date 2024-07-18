package com.teamoffroad.feature.auth.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.GenderHintButton

@Composable
internal fun SetGenderScreen(
    nickname: String,
    birthDate: String?,
    navigateToSetCharacter: () -> Unit,
    viewModel: SetGenderViewModel = hiltViewModel(),
) {

    val isSuccess by viewModel.isSuccess.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isError by viewModel.isError.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                text = "성별을 선택해 주세요.",
                color = Main2,
                style = OffroadTheme.typography.subtitleReg,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
            SetGenderButton(viewModel)
            Spacer(modifier = Modifier.weight(1f))
            OffroadBasicBtn(
                modifier = Modifier
                    .width(312.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { viewModel.fetchUserProfile(nickname, birthDate) },
                isActive = true,
                text = "다음"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (isSuccess) navigateToSetCharacter()
    if (isError) Toast.makeText(LocalContext.current, "네트워크 연결을 확인해 주세요.", Toast.LENGTH_SHORT).show()
}

@Composable
fun SetGenderButton(viewModel: SetGenderViewModel) {
    var click by remember { mutableIntStateOf(0) }

    val (male, female, other) = if (click == 1) {
        Triple(true, false, false)
    } else if (click == 2) {
        Triple(false, true, false)
    } else if (click == 3) {
        Triple(false, false, true)
    } else {
        Triple(false, false, false)
    }

    Column {
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickable(
                    onClick = {
                        click = 1
                        viewModel.updateCheckedGender("MALE")
                    }),
            value = "남성",
            isActive = male
        )
        GenderHintButton(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickable(
                    onClick = {
                        click = 2
                        viewModel.updateCheckedGender("FEMALE")
                    }),
            value = "여성",
            isActive = female
        )
        GenderHintButton(
            modifier = Modifier
                .clickable(
                    onClick = {
                        click = 3
                        viewModel.updateCheckedGender("OTHER")
                    }),
            value = "기타",
            isActive = other
        )
    }
}