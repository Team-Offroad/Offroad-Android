package com.teamoffroad.feature.auth.presentation

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SelectedCharacterText
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SelectedCharacterScreen(
    selectedCharacterUrl: String,
    navigateToHome: () -> Unit,
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF10101A),
            Color(0xFF1A1A4D),
            Color(0xFF1E1A78),
            Color(0xFF4F4CBB),
            Color(0xFF3E3B99),
            Color(0xFF4C4A9A),
        )
    )
    val overlayBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF1A1A4D).copy(alpha = 0.3f),
            Color(0xFF4F4CBB).copy(alpha = 0.1f),
            Color(0xFF1A1A4D).copy(alpha = 0.3f),
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
            fontSize = 22.sp,
            color = SelectedCharacterText,
            style = OffroadTheme.typography.title,
        )
        Text(
            modifier = Modifier
                .padding(bottom = 100.dp),
            text = stringResource(R.string.auth_selected_character_sub_title),
            fontSize = 22.sp,
            color = SelectedCharacterText,
            style = OffroadTheme.typography.title
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 60.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(selectedCharacterUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "character"
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