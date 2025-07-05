package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.explore.presentation.component.CourseQuestMap
import com.teamoffroad.feature.explore.presentation.component.CourseQuestPlacesContainer
import com.teamoffroad.feature.explore.presentation.util.CourseQuestExploreAuthStateHandler
import com.teamoffroad.offroad.feature.explore.R
import kotlinx.coroutines.delay

private const val TOUCH_EVENT_THRESHOLD: Long = 100L

@Composable
fun CourseQuestDetailScreen(
    questId: Long,
    deadline: String,
    dDay: Int,
    navigateToBack: () -> Unit,
    viewModel: CourseQuestDetailViewModel = hiltViewModel(),
) {
    val places = viewModel.places.collectAsStateWithLifecycle()
    val location = viewModel.location.collectAsStateWithLifecycle()
    val exploreAuthState = viewModel.exploreAuthState.collectAsStateWithLifecycle()

    val mapHeight = 218.dp

    val mapViewHeight = remember { mutableFloatStateOf(0f) }
    val isTouchingMapArea = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadQuestDetails(questId)
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
            CourseQuestMap(
                mapHeight = mapHeight,
                mapHeightPx = mapViewHeight,
                location = location.value,
                places = places.value,
                onLocationChange = viewModel::updateLocation,
            )

            CourseQuestPlacesContainer(
                places = places.value.places,
                deadline = deadline,
                dDay = dDay,
                mapHeight = mapHeight,
                isTouchingMapArea = isTouchingMapArea,
                mapHeightPx = mapViewHeight,
                onVisitClick = viewModel::performExplore,
            )
        }
    }

    CourseQuestExploreAuthStateHandler(places.value, exploreAuthState.value, viewModel::updateExploreAuthState)
}
