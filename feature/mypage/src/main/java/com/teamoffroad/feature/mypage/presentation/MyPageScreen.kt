package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.core.designsystem.component.OffroadActionBar

@Composable
internal fun MypageScreen() {
    Column {
        OffroadActionBar(Color.Transparent)
        Text(
            "mypage screen",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
    }
    OnBackButtonListener()
}
