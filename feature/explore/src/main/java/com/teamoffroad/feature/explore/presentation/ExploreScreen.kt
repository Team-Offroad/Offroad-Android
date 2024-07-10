package com.teamoffroad.feature.explore.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.teamoffroad.feature.explore.presentation.model.LocationModel

@Composable
internal fun ExploreScreen(
    locationState: LocationModel,
    navigateToHome: () -> Unit,
    updatePermission: (Boolean, Boolean) -> Unit,
) {

    val context = LocalContext.current

    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val isPermissionGranted = permissionsMap.values.all { it }
        when (isPermissionGranted) {
            true -> updatePermission(true, false)
            false -> updatePermission(false, true)
        }
    }

    LaunchedEffect(locationState.isAlreadyHavePermission) {
        if (locationState.isAlreadyHavePermission) return@LaunchedEffect
        val fineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        when (!fineLocationGranted || !coarseLocationGranted) {
            true -> launcherMultiplePermissions.launch(permissions)
            false -> updatePermission(true, false)
        }
    }

    Column {
        if (locationState.isAlreadyHavePermission) {
            Text("Location permissions granted")
        }
        if (locationState.isPermissionRejected) {
            navigateToHome()
        }
    }
}
