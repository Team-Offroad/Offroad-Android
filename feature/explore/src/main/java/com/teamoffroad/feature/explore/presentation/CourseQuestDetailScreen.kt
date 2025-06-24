package com.teamoffroad.feature.explore.presentation

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.feature.explore.domain.model.Location.Companion.toLatLng
import com.teamoffroad.feature.explore.presentation.component.CourseQuestPlaceItem
import com.teamoffroad.feature.explore.presentation.component.DeadlineHeader
import com.teamoffroad.feature.explore.presentation.component.RewardBox
import com.teamoffroad.feature.explore.presentation.component.getCategoryOverlayImage
import java.time.LocalDateTime

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun CourseQuestDetailScreen(
    questId: Long,
    deadline: String,
    dDay: Int,
    reward: String,
    navigateToBack: () -> Unit,
    viewModel: CourseQuestDetailViewModel = hiltViewModel(),
) {
    val quests = viewModel.quests.collectAsStateWithLifecycle()
    val location = viewModel.location.collectAsStateWithLifecycle()
    val mapHeight = 218.dp
    val rewardBoxHeight = 88.dp
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadQuestDetails(questId)
    }

    Column(
        modifier =
            Modifier
                .navigationPadding()
                .fillMaxSize()
                .background(Main1)
                .actionBarPadding(),
    ) {
        NavigateBackAppBar(
            text = "퀘스트 목록",
            modifier = Modifier.padding(top = 20.dp),
            navigateToBack = { navigateToBack() },
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Spacer(
                        modifier =
                            Modifier
                                .height(mapHeight)
                                .fillMaxWidth(),
                    )
                }

                item {
                    DeadlineHeader(
                        deadline = LocalDateTime.parse(deadline),
                        dDay = "D-$dDay",
                    )
                }

                itemsIndexed(quests.value.places) { index, place ->
                    val isFirst = index == 0
                    val isLast = index == quests.value.places.lastIndex

                    CourseQuestPlaceItem(
                        quest = place,
                        isFirst = isFirst,
                        isLast = isLast,
                        onVisitClick = { viewModel.performExplore(place) },
                    )
                }

                item {
                    Spacer(
                        modifier =
                            Modifier
                                .background(Main1)
                                .height(rewardBoxHeight),
                    )
                }
            }

            NaverMap(
                properties = MapProperties(locationTrackingMode = LocationTrackingMode.NoFollow),
                uiSettings =
                    MapUiSettings(
                        isScaleBarEnabled = false,
                        isZoomControlEnabled = false,
                        isLogoClickEnabled = false,
                        isCompassEnabled = false,
                        logoGravity = Gravity.TOP,
                        logoMargin = PaddingValues(top = 28.dp, start = 22.dp),
                    ),
                locationSource = rememberFusedLocationSource(isCompassEnabled = true),
                cameraPositionState =
                    CameraPositionState(
                        CameraPosition(quests.value.centerLocation.toLatLng(), 13.5),
                    ),
                onLocationChange = { location ->
                    viewModel.updateLocation(location.latitude, location.longitude)
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(mapHeight)
                        .align(Alignment.TopCenter),
            ) {
                LocationOverlay(
                    position = location.value.toLatLng(),
                    icon = OverlayImage.fromBitmap(createBitmap(1, 1)),
                    circleColor = Transparent,
                )

                quests.value.places.forEach { place ->
                    Marker(
                        state = MarkerState(position = place.position.toLatLng()),
                        icon = OverlayImage.fromBitmap(getCategoryOverlayImage(context, place.category)),
                    )
                }
            }

            RewardBox(
                reward = reward,
                isComplete = quests.value.isComplete,
                modifier =
                    Modifier
                        .background(Main1)
                        .padding(horizontal = 24.dp)
                        .height(rewardBoxHeight)
                        .align(Alignment.BottomCenter),
            )
        }
    }
}
