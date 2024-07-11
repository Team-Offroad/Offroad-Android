package com.teamoffroad.feature.explore.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
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

    ExplorePermissionsHandler(
        context = context,
        locationState = locationState,
        launcherMultiplePermissions = launcherMultiplePermissions,
        permissions = permissions,
        updatePermission = updatePermission
    )

    if (locationState.isAlreadyHavePermission) {
        ExploreNaverMap(locationState)
    }
    if (locationState.isPermissionRejected) {
        ExplorePermissionRejectedHandler(context, navigateToHome)
    }
    ExploreAppBar()
}

@Composable
private fun getLocationPermissionLauncher(
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
private fun ExplorePermissionsHandler(
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
private fun ExplorePermissionRejectedHandler(
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

@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun ExploreNaverMap(
    locationState: LocationModel,
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                isMountainLayerGroupEnabled = true,
                locationTrackingMode = locationState.locationTrackingMode,
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true)
        )
    }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(locationState.location, 15.0)
    }

    Box(Modifier.fillMaxSize().padding(bottom = 74.dp)) {
        NaverMap(
            properties = mapProperties,
            uiSettings = mapUiSettings,
            locationSource = rememberFusedLocationSource(),
            cameraPositionState = cameraPositionState,
            onLocationChange = { location ->
                // TODO: 거리계산 로직 추가
            },
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExploreAppBar() {
    Column {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = Main1,
                titleContentColor = Main2,
            ),
            title = {
                Text(
                    stringResource(R.string.explore_title),
                    textAlign = TextAlign.Start,
                    style = OffroadTheme.typography.subtitle2Semibold,
                    modifier = Modifier.padding(top = 32.dp),
                )
            },
            modifier = Modifier.height(70.dp),
        )
        HorizontalDivider(
            color = Gray100,
            modifier = Modifier.height(1.dp),
        )
    }
}

private const val LOCATION_PERMISSION_REQUEST_CODE = 1000