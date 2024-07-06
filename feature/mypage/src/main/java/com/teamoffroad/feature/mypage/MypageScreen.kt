package com.teamoffroad.feature.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun MypageScreen(
    padding: PaddingValues,
) {
    Text(
        "mypage screen",
        fontSize = 40.sp,
        textAlign = TextAlign.Center
    )
}