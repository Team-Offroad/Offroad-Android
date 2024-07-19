package com.teamoffroad.feature.home.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun HomeRoute(
) {
    HomeScreen(
        category = ""
    )
}