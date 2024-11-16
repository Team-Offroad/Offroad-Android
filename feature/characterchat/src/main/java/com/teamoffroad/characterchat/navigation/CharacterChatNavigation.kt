package com.teamoffroad.characterchat.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.characterchat.presentation.CharacterChatScreen
import com.teamoffroad.core.navigation.CharacterChatRoute
import com.teamoffroad.feature.home.presentation.HomeScreen

fun NavController.navigateToCharacterChat() {
    navigate(CharacterChatRoute.CharacterChat)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.characterChatNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<CharacterChatRoute.CharacterChat> {
        Box(modifier = Modifier
            .graphicsLayer {
                renderEffect = BlurEffect(
                    radiusX = 120f, radiusY = 120f
                )
                alpha = DefaultAlpha
            }) {
            HomeScreen(category = null)
        }
        CharacterChatScreen(
            navigateToBack = navigateToBack,
        )
    }
}
