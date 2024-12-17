package com.teamoffroad.feature.explore.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.teamoffroad.feature.explore.presentation.component.ExploreFailedDialogContent
import com.teamoffroad.feature.explore.presentation.component.ExploreResultDialog
import com.teamoffroad.feature.explore.presentation.component.ExploreSuccessDialogContent
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreAuthStateHandler(
    uiState: ExploreUiState,
    updateExploreAuthState: (ExploreAuthState) -> Unit,
    navigateToHome: (String, List<String>) -> Unit,
) {
    when (uiState.authResultType) {
        is ExploreAuthState.LocationError -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.LocationError(),
                previousText = stringResource(R.string.explore_location_failed_label_previous),
                boldText = stringResource(R.string.explore_location_failed_label_bold),
                nextText = stringResource(R.string.explore_location_failed_label_next),
                content = {
                    ExploreFailedDialogContent(
                        imageUrl = uiState.authResultType.characterImageUrl
                    )
                },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        ExploreAuthState.EtcError -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.EtcError,
                previousText = stringResource(R.string.explore_etc_failed_label),
                content = { ExploreFailedDialogContent(imageUrl = "") },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        is ExploreAuthState.Success -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.Success(),
                previousText = stringResource(R.string.explore_dialog_success),
                content = { ExploreSuccessDialogContent(url = uiState.authResultType.characterImageUrl) },
                onDismissRequest = {
                    updateExploreAuthState(ExploreAuthState.None)
                    navigateToHome(uiState.authResultType.category.name, uiState.authResultType.completeQuests)
                }
            )
        }

        else -> {}
    }
}