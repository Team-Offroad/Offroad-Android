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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.overlay.OverlayImage
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.explore.domain.model.MapPosition.Companion.toLatLng
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
    courseQuestDetailViewModel: CourseQuestDetailViewModel = hiltViewModel(),
) {
    val quests = courseQuestDetailViewModel.quests.collectAsStateWithLifecycle()
    val mapHeight = 218.dp
    val rewardBoxHeight = 88.dp
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        courseQuestDetailViewModel.loadQuestDetails(questId)
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
        ) { navigateToBack() }
        Box {
            NaverMap(
                uiSettings =
                    MapUiSettings(
                        isScaleBarEnabled = false,
                        isZoomControlEnabled = false,
                        isLogoClickEnabled = false,
                        isCompassEnabled = false,
                        logoGravity = Gravity.TOP,
                        logoMargin = PaddingValues(top = 28.dp, start = 22.dp),
                    ),
                cameraPositionState = CameraPositionState(CameraPosition(quests.value.centerMapPosition.toLatLng(), 13.5)),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(mapHeight),
            ) {
                quests.value.places.forEach { place ->
                    Marker(
                        state = MarkerState(position = place.position.toLatLng()),
                        icon = OverlayImage.fromBitmap(getCategoryOverlayImage(context, place.category)),
                    )
                }
            }
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
            ) {
                Spacer(
                    modifier =
                        Modifier
                            .height(mapHeight)
                            .fillMaxWidth(),
                )

                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Main1),
                ) {
                    DeadlineHeader(
                        deadline = LocalDateTime.parse(deadline),
                        dDay = "D-$dDay",
                    )

                    quests.value.places.forEach {
                        CourseQuestPlaceItem(quest = it, onVisitClick = {})
                    }
                }

                Spacer(modifier = Modifier.height(rewardBoxHeight))
            }
            RewardBox(
                reward = reward,
                isComplete = false,
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
