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
fun ExplorePermissionHandler(
    context: Context,
    uiState: ExploreUiState,
    updatePermission: (Boolean) -> Unit,
) {
    val launcherLocationPermissions = getPermissionsLauncher(updatePermission)

    LaunchedEffect(uiState.isLocationPermissionGranted) {
        val isFineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        when (isFineLocationGranted) {
            true -> updatePermission(true)
            false -> {
                launcherLocationPermissions.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    )
                )
            }
        }
    }
}

@Composable
private fun getPermissionsLauncher(
    updatePermission: (Boolean) -> Unit,
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isPermissionGranted ->
        when (isPermissionGranted.values.all { it }) {
            true -> updatePermission(true)
            false -> updatePermission(false)
        }
    }
}
