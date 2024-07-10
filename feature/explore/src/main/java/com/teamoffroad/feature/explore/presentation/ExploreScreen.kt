package com.teamoffroad.feature.explore.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.teamoffroad.feature.explore.presentation.model.LocationModel
import com.teamoffroad.offroad.feature.explore.R

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

    val launcherMultiplePermissions = getLocationPermissionLauncher(updatePermission)

    HandlePermissions(
        context = context,
        locationState = locationState,
        launcherMultiplePermissions = launcherMultiplePermissions,
        permissions = permissions,
        updatePermission = updatePermission
    )

    if (locationState.isAlreadyHavePermission) {
        Text("Location permissions granted")
    }
    if (locationState.isPermissionRejected) {
        HandlePermissionRejected(context, navigateToHome)
    }
}

@Composable
fun getLocationPermissionLauncher(
    updatePermission: (Boolean, Boolean) -> Unit,
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val isPermissionGranted = permissionsMap.values.all { it }
        when (isPermissionGranted) {
            true -> updatePermission(true, false)
            false -> updatePermission(false, true)
        }
    }
}

@Composable
fun HandlePermissions(
    context: Context,
    locationState: LocationModel,
    launcherMultiplePermissions: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    permissions: Array<String>,
    updatePermission: (Boolean, Boolean) -> Unit,
) {
    LaunchedEffect(locationState.isAlreadyHavePermission) {
        if (locationState.isAlreadyHavePermission) return@LaunchedEffect

        val isFineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isCoarseLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        when (!isFineLocationGranted || !isCoarseLocationGranted) {
            true -> launcherMultiplePermissions.launch(permissions)
            false -> updatePermission(true, false)
        }
    }
}

@Composable
fun HandlePermissionRejected(
    context: Context,
    navigateToHome: () -> Unit,
) {
    Toast.makeText(
        context,
        stringResource(R.string.explore_location_permission_failed),
        Toast.LENGTH_SHORT
    ).show()
    navigateToHome()
}
