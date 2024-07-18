package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SelectedCharacterBackground
import com.teamoffroad.core.designsystem.theme.SelectedCharacterBottom
import com.teamoffroad.core.designsystem.theme.SelectedCharacterText
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn

@Composable
internal fun SelectedCharacterScreen(
    selectedCharacterUrl: String,
    navigateToHome: () -> Unit,
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SelectedCharacterBackground),
        ) {
            OffroadActionBar(SelectedCharacterBackground)
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(118.dp))
                    Text(
                        text =
                        "프로필 생성을 축하드려요!",
                        fontSize = 22.sp,
                        color = SelectedCharacterText,
                        style = OffroadTheme.typography.title
                    )
                    Text(
                        text = "지금 바로 모험을 떠나볼까요?",
                        fontSize = 22.sp,
                        color = SelectedCharacterText,
                        style = OffroadTheme.typography.title
                    )
                    Spacer(modifier = Modifier.height(330.dp))
                }
            }
            Column(
                modifier = Modifier
                    .background(SelectedCharacterBottom)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(
                    modifier = Modifier.height(
                        162.dp
                    )
                )
                OffroadBasicBtn(
                    text =
                    "모험 시작하기",
                    isActive = true,
                    modifier = Modifier
                        .width(312.dp)
                        .height(50.dp),
                    onClick = navigateToHome
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(SelectedCharacterBottom)
            )
        }
    }
}
