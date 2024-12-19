package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1

@Composable
fun SupportScreen(
    navigateToBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .navigationPadding()
            .background(Main1)
            .fillMaxSize(),
    ) {
        
    }
}