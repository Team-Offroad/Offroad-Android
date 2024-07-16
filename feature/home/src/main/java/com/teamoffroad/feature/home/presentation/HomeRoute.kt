package com.teamoffroad.feature.home.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.feature.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun HomeRoute(
    padding: PaddingValues,
) {
    HomeScreen(
        padding = padding,
    )
}