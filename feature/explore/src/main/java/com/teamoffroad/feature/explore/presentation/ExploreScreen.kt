package com.teamoffroad.feature.explore.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.view.Gravity
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.component.ExploreMapBottomButton
import com.teamoffroad.feature.explore.presentation.model.LocationModel
import com.teamoffroad.offroad.feature.explore.R

@Composable
internal fun ExploreScreen(
    locationState: LocationModel,
    navigateToHome: () -> Unit,
    updatePermission: (Boolean, Boolean) -> Unit,
    updateLocation: (Double, Double) -> Unit,
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
        ExploreNaverMap(locationState, updateLocation)
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
        context, stringResource(R.string.explore_location_permission_failed), Toast.LENGTH_SHORT
    ).show()
    navigateToHome()
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun ExploreNaverMap(
    locationState: LocationModel,
    updateLocation: (Double, Double) -> Unit,
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(locationTrackingMode = locationState.locationTrackingMode)
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isScaleBarEnabled = false, isZoomControlEnabled = false, isLogoClickEnabled = false, logoGravity = Gravity.TOP)
        )
    }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(locationState.location, 15.0)
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 70.dp, bottom = 74.dp)
    ) {
        NaverMap(
            properties = mapProperties,
            uiSettings = mapUiSettings,
            locationSource = rememberFusedLocationSource(),
            cameraPositionState = cameraPositionState,
            onLocationChange = { location ->
                updateLocation(location.latitude, location.longitude)
            },
        ) {
            LocationOverlay(
                position = locationState.location,
                icon = OverlayImage.fromResource(R.drawable.ic_explore_refresh),
                subIcon = OverlayImage.fromResource(R.drawable.ic_explore_location),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp)
                .align(Alignment.TopCenter),
        ) {
            ExploreRefreshButton(
                text = "현 지도에서 검색",
                onClick = {},
                modifier = Modifier.align(Alignment.Center),
            )
            ExploreTrackingButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)
                    .padding(end = 22.dp),
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            ExploreMapBottomButton(
                painter = painterResource(R.drawable.ic_explore_quest_list),
                text = "퀘스트 목록",
                onClick = {},
            )
            Spacer(modifier = Modifier.size(16.dp))
            ExploreMapBottomButton(
                painter = painterResource(R.drawable.ic_explore_location),
                text = "장소 목록",
                onClick = {},
            )
        }
    }
}

@Composable
private fun ExploreRefreshButton(
    modifier: Modifier,
    text: String,
    radius: Int = 6,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(126.dp)
            .height(32.dp)
            .border(1.dp, Gray100, shape = RoundedCornerShape(radius.dp))
            .shadow(4.dp, shape = RoundedCornerShape(radius.dp))
            .background(color = White, shape = RoundedCornerShape(radius.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_explore_refresh),
                contentDescription = text,
                modifier = Modifier.size(22.dp),
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = text,
                color = Gray400,
                style = OffroadTheme.typography.hint,
            )
        }
    }
}

@Composable
private fun ExploreTrackingButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .clickable { onClick() }
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.Center)
                    .shadow(elevation = 4.dp, shape = CircleShape)
            )
            Image(
                painter = painterResource(R.drawable.ic_explore_tracking_no_follow),
                contentDescription = "트래킹 모드 전환",
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.Center),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
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
