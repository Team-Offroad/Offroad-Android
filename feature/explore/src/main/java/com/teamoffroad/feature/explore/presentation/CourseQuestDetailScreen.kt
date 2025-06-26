package com.teamoffroad.feature.explore.presentation

import android.view.Gravity
import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.teamoffroad.feature.explore.presentation.util.CourseQuestExploreAuthStateHandler
import com.teamoffroad.offroad.feature.explore.R
import kotlinx.coroutines.delay
import java.time.LocalDateTime

private const val TOUCH_EVENT_THRESHOLD: Long = 100L
private const val DEFAULT_MAP_ZOOM: Double = 13.5

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
    val places = viewModel.places.collectAsStateWithLifecycle()
    val location = viewModel.location.collectAsStateWithLifecycle()
    val exploreAuthState = viewModel.exploreAuthState.collectAsStateWithLifecycle()
    val mapHeight = 218.dp
    val rewardBoxHeight = 88.dp
    val listState = rememberLazyListState()
    val context = LocalContext.current
    val mapHeightPx = remember { mutableFloatStateOf(0f) }
    val isTouchingMapArea = remember { mutableStateOf(false) }
    val cameraPositionState = remember { CameraPositionState() }

    val scrollOffsetPx =
        remember {
            derivedStateOf {
                val firstIndex = listState.firstVisibleItemIndex
                val offset = listState.firstVisibleItemScrollOffset
                if (firstIndex == 0) offset.toFloat() else mapHeightPx.floatValue
            }
        }

    val scrollBlocker =
        remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset = if (isTouchingMapArea.value) available else Offset.Zero
            }
        }

    LaunchedEffect(Unit) {
        viewModel.loadQuestDetails(questId)
    }

    LaunchedEffect(places.value.centerLocation) {
        cameraPositionState.position =
            CameraPosition(
                places.value.centerLocation.toLatLng(),
                DEFAULT_MAP_ZOOM,
            )
    }

    LaunchedEffect(isTouchingMapArea.value) {
        if (isTouchingMapArea.value) {
            delay(TOUCH_EVENT_THRESHOLD)
            isTouchingMapArea.value = false
        }
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
            text = stringResource(R.string.explore_course_quest_quests),
            modifier = Modifier.padding(top = 20.dp),
            navigateToBack = { navigateToBack() },
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(mapHeight)
                        .align(Alignment.TopCenter)
                        .onGloballyPositioned { layoutCoordinates ->
                            mapHeightPx.floatValue = layoutCoordinates.size.height.toFloat()
                        },
            ) {
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
                    cameraPositionState = cameraPositionState,
                    onLocationChange = { location ->
                        viewModel.updateLocation(location.latitude, location.longitude)
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(mapHeight),
                ) {
                    LocationOverlay(
                        position = location.value.toLatLng(),
                        icon = OverlayImage.fromBitmap(createBitmap(1, 1)),
                        circleColor = Transparent,
                    )

                    places.value.places.forEach { place ->
                        Marker(
                            state = MarkerState(position = place.position.toLatLng()),
                            icon = OverlayImage.fromBitmap(getCategoryOverlayImage(context, place.category)),
                        )
                    }
                }
            }

            LazyColumn(
                state = listState,
                userScrollEnabled = mapHeightPx.floatValue != 0f && !isTouchingMapArea.value,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBlocker)
                        .pointerInteropFilter { event ->
                            val shownMapHeight = mapHeightPx.floatValue - scrollOffsetPx.value

                            when (event.actionMasked) {
                                ACTION_DOWN, ACTION_MOVE -> isTouchingMapArea.value = event.y < shownMapHeight
                                ACTION_UP, ACTION_CANCEL -> isTouchingMapArea.value = false
                            }
                            false
                        },
            ) {
                item {
                    Spacer(
                        modifier = Modifier.height(mapHeight),
                    )
                }
                item {
                    DeadlineHeader(
                        deadline = LocalDateTime.parse(deadline),
                        dDay = "D-$dDay",
                    )
                }

                itemsIndexed(places.value.places) { index, place ->
                    val isFirst = index == 0
                    val isLast = index == places.value.places.lastIndex

                    CourseQuestPlaceItem(
                        quest = place,
                        isFirst = isFirst,
                        isLast = isLast,
                        onVisitClick = {
                            viewModel.performExplore(place)
                        },
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

            RewardBox(
                reward = reward,
                isComplete = places.value.isComplete,
                modifier =
                    Modifier
                        .background(Main1)
                        .padding(horizontal = 24.dp)
                        .height(rewardBoxHeight)
                        .align(Alignment.BottomCenter),
            )
        }
    }

    CourseQuestExploreAuthStateHandler(places.value, exploreAuthState.value, viewModel::updateExploreAuthState)
}
