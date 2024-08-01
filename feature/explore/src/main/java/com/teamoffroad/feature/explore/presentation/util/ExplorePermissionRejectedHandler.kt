package com.teamoffroad.feature.explore.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExplorePermissionRejectedHandler(
    context: Context,
    uiState: ExploreUiState,
    navigateToHome: (String?) -> Unit,
    updatePermission: (Boolean?, Boolean, Boolean) -> Unit,
) {
    val toastMessage = when {
        !uiState.isCameraPermissionGranted -> stringResource(R.string.explore_camera_permission_failed)

        !uiState.isLocationPermissionGranted -> stringResource(R.string.explore_location_permission_failed)

        else -> ""
    }

    if (toastMessage.isNotBlank()) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        updatePermission(null, false, false)
        navigateToHome(null)
    }
}