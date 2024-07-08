package com.teamoffroad.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun AuthScreen(
    padding: PaddingValues,
    onAuthBtnClick: () -> Unit,
) {
    Box() {
        Button(
            onClick = onAuthBtnClick,
        ) {
            Text("Auth Button")
        }
    }
}