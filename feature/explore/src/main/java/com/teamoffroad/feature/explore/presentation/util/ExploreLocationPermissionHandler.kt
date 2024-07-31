package com.teamoffroad.feature.explore.presentation.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState

@Composable
fun ExploreLocationPermissionHandler(
    context: Context,
    uiState: ExploreUiState,
    updatePermission: (Boolean?, Boolean, Boolean) -> Unit,
) {
    val launcherLocationPermissions = getPermissionsLauncher(updatePermission)

    LaunchedEffect(uiState.isAllPermissionGranted) {
        val isFineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isCameraGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!isFineLocationGranted || !isCameraGranted) {
            launcherLocationPermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                )
            )
        } else {
            updatePermission(null, true, true)
        }
    }
}

@Composable
private fun getPermissionsLauncher(
    updatePermission: (Boolean?, Boolean, Boolean) -> Unit,
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isPermissionGranted ->
        val isLocationNotGranted =
            isPermissionGranted[Manifest.permission.ACCESS_FINE_LOCATION] == false
        val isCameraNotGranted = isPermissionGranted[Manifest.permission.CAMERA] == false

        if (isPermissionGranted.values.all { it }) {
            updatePermission(false, true, true)
        }

        when {
            isCameraNotGranted -> updatePermission(true, true, false)

            isLocationNotGranted -> updatePermission(true, false, true)
        }
    }
}
