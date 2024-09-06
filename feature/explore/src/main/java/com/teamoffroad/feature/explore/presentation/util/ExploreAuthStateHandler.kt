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
    qrResultImageUrl: String?,
    navigateToHome: (String) -> Unit,
) {
    val imageUrl = when (uiState.authResultType) {
        is ExploreAuthState.Success -> uiState.authResultType.characterImageUrl
        else -> qrResultImageUrl
    }
    
    when (uiState.authResultType) {
        ExploreAuthState.LocationError -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.LocationError,
                text = stringResource(R.string.explore_location_failed_label),
                content = {
                    ExploreFailedDialogContent(
                        imageUrl = stringResource(R.string.explore_failed_img)
                    )
                },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        ExploreAuthState.CodeError -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.CodeError,
                text = stringResource(R.string.explore_code_failed_label),
                content = { ExploreFailedDialogContent(imageUrl = imageUrl) },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        ExploreAuthState.EtcError -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.EtcError,
                text = stringResource(R.string.explore_etc_failed_label),
                content = { ExploreFailedDialogContent(imageUrl = stringResource(R.string.explore_failed_img)) },
                onDismissRequest = { updateExploreAuthState(ExploreAuthState.None) }
            )
        }

        is ExploreAuthState.Success -> {
            ExploreResultDialog(
                errorType = ExploreAuthState.Success(),
                text = stringResource(R.string.explore_dialog_success),
                content = { ExploreSuccessDialogContent(url = imageUrl) },
                onDismissRequest = {
                    updateExploreAuthState(ExploreAuthState.None)
                    navigateToHome(uiState.authResultType.category.name)
                }
            )
        }

        else -> {}
    }
}