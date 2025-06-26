package com.teamoffroad.feature.explore.presentation.util

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.OrbSnackBar
import com.teamoffroad.core.designsystem.component.SHOWN_SNACK_BAR_DURATION
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.feature.explore.presentation.component.ExploreResultDialog
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlacesUiModel
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.offroad.feature.explore.R
import kotlinx.coroutines.delay

private const val DUPLICATE_SNACK_BAR_OFFSET = 300L

@Composable
fun CourseQuestExploreAuthStateHandler(
    places: CourseQuestPlacesUiModel,
    exploreAuthState: ExploreAuthState,
    updateExploreAuthState: (ExploreAuthState) -> Unit,
) {
    val isSnackBarShown = remember { mutableStateOf(false) }

    LaunchedEffect(exploreAuthState) {
        isSnackBarShown.value = false
        delay(DUPLICATE_SNACK_BAR_OFFSET)
        if (exploreAuthState is ExploreAuthState.Success || exploreAuthState is ExploreAuthState.LocationError) {
            isSnackBarShown.value = true
        }
    }

    LaunchedEffect(isSnackBarShown.value) {
        if (isSnackBarShown.value) {
            delay(SHOWN_SNACK_BAR_DURATION)
            isSnackBarShown.value = false
        }
    }

    when (exploreAuthState) {
        is ExploreAuthState.LocationError -> {
            ExploreResultDialog(
                state = ExploreAuthState.DuplicateError(),
                title = stringResource(R.string.explore_course_quest_visit_failed_title),
                description = stringResource(R.string.explore_course_quest_distance_failed_label),
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) },
            )
        }

        is ExploreAuthState.DuplicateError -> {
            ExploreResultDialog(
                state = ExploreAuthState.DuplicateError(),
                title = stringResource(R.string.explore_course_quest_visit_failed_title),
                description = stringResource(R.string.explore_duplicate_failed_label),
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) },
            )
        }

        ExploreAuthState.EtcError -> {
            ExploreResultDialog(
                state = ExploreAuthState.EtcError,
                title = stringResource(R.string.explore_course_quest_visit_failed_title),
                description = stringResource(R.string.explore_etc_failed_label),
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) },
            )
        }

        is ExploreAuthState.Success -> {
            OrbSnackBar(
                isVisible = isSnackBarShown.value,
                text = stringResource(R.string.explore_course_quest_visit_success_label, places.leftCount),
                modifier =
                    Modifier
                        .actionBarPadding()
                        .padding(top = 64.dp),
            )
        }

        ExploreAuthState.None -> {}
    }
}
