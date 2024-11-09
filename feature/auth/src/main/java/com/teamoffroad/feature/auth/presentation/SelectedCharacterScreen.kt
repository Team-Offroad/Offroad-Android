package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Ground
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SelectedCharacterText
import com.teamoffroad.core.designsystem.theme.Wall
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun SelectedCharacterScreen(
    selectedCharacterUrl: String,
    navigateToHome: () -> Unit,
) {
    OffroadTheme {
        Box {
            Column(
                modifier = Modifier
                    .navigationPadding()
                    .fillMaxSize()
                    .background(Wall),
            ) {
                OffroadActionBar(Wall)
                Spacer(modifier = Modifier.padding(270.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Ground)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    OffroadBasicBtn(
                        text = stringResource(R.string.auth_selected_character_button),
                        isActive = true,
                        modifier = Modifier
                            .padding(bottom = 72.dp)
                            .width(312.dp)
                            .height(50.dp),
                        onClick = {navigateToHome()}
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 156.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_auth_selected_character_light),
                    contentDescription = ""
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 118.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.auth_selected_character_title),
                        fontSize = 22.sp,
                        color = SelectedCharacterText,
                        style = OffroadTheme.typography.title,
                    )
                    Text(
                        text = stringResource(R.string.auth_selected_character_sub_title),
                        fontSize = 22.sp,
                        color = SelectedCharacterText,
                        style = OffroadTheme.typography.title
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 350.dp)
                    .padding(bottom = 174.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.BottomCenter,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedCharacterUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = "character"
                )
            }
        }
    }
}
