package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teamoffroad.core.designsystem.theme.Main1

@Composable
internal fun SetNicknameScreen(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1
    ){
        Button(onClick = navigateToHome){
            Text("aSDASDSds")
        }

    }
}