package com.teamoffroad.feature.explore.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    viewModel: ExploreCameraViewModel = hiltViewModel(),
) {
    Text(text = "$placeId, $latitude, $longitude")
}
