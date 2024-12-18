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
                text = stringResource(R.string.explore_location_failed_label),
                content = {
                    ExploreFailedDialogContent(
                        url = uiState.authResultType.characterImageUrl,
                    )
                },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        ExploreAuthState.EtcError -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.EtcError,
                text = stringResource(R.string.explore_etc_failed_label),
                content = {
                    ExploreFailedDialogContent(
                        url = "https://github.com/user-attachments/assets/80c32e0e-8342-4e6b-af47-ab11532cbf4d",
                    )
                },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        is ExploreAuthState.Success -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.Success(),
                text = stringResource(R.string.explore_dialog_success_label),
                content = { ExploreSuccessDialogContent(url = uiState.authResultType.characterImageUrl) },
                onDismissRequest = {
                    updateExploreAuthState(ExploreAuthState.None)
                    navigateToHome(uiState.authResultType.category.name, uiState.authResultType.completeQuests)
                },
            )
        }

        else -> {}
    }
}