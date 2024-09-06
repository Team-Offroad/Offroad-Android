package com.teamoffroad.feature.auth.presentation

import android.text.Layout.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1

@Composable
internal fun AgreeTermsAndConditionsScreen(
    navigateToSetNicknameScreen: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Main1)
    ) {
        Spacer(Modifier.height(30.dp))
        Text(text = "ASdsada",
            modifier = Modifier.clickable { navigateToSetNicknameScreen() })
    }

}