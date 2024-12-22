package com.teamoffroad.feature.auth.presentation.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SelectedCharacterGradi1
import com.teamoffroad.core.designsystem.theme.SelectedCharacterGradi2
import com.teamoffroad.core.designsystem.theme.SelectedCharacterGradi3
import com.teamoffroad.core.designsystem.theme.SelectedCharacterGradi4
import com.teamoffroad.core.designsystem.theme.SelectedCharacterGradi5
import com.teamoffroad.core.designsystem.theme.SelectedCharacterGradi6
import com.teamoffroad.core.designsystem.theme.SelectedCharacterText
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SelectedCharacterScreen(
    selectedCharacterUrl: String,
    navigateToHome: () -> Unit,
) {
    BackHandler {
        navigateToHome()
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            SelectedCharacterGradi1,
            SelectedCharacterGradi2,
            SelectedCharacterGradi3,
            SelectedCharacterGradi4,
            SelectedCharacterGradi5,
            SelectedCharacterGradi6,
        )
    )
    val overlayBrush = Brush.horizontalGradient(
        colors = listOf(
            SelectedCharacterGradi2.copy(alpha = 0.3f),
            SelectedCharacterGradi4.copy(alpha = 0.1f),
            SelectedCharacterGradi2.copy(alpha = 0.3f),
        )
    )
    Column(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .fillMaxSize()
            .background(gradientBrush)
            .background(overlayBrush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OffroadActionBar()
        Text(
            modifier = Modifier
                .padding(top = 70.dp, bottom = 5.dp),
            text = stringResource(R.string.auth_selected_character_title),
            fontSize = with(LocalDensity.current) { 22.dp.toSp() },
            color = SelectedCharacterText,
            style = OffroadTheme.typography.title,
        )
        Text(
            modifier = Modifier
                .padding(bottom = 100.dp),
            text = stringResource(R.string.auth_selected_character_sub_title),
            fontSize = with(LocalDensity.current) { 22.dp.toSp() },
            color = SelectedCharacterText,
            style = OffroadTheme.typography.title
        )
        AdaptationImage(
            imageUrl = selectedCharacterUrl,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 78.dp)
                .weight(1f)
                .padding(bottom = 60.dp),
        )
        OffroadBasicBtn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.auth_selected_character_button),
            isActive = true,
            onClick = { navigateToHome() }
        )
    }
}