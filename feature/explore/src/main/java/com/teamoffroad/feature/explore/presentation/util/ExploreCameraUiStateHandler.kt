package com.teamoffroad.feature.explore.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.feature.explore.presentation.ExploreViewModel
import com.teamoffroad.feature.explore.presentation.component.ExploreFailedDialogContent
import com.teamoffroad.feature.explore.presentation.component.ExploreResultDialog
import com.teamoffroad.feature.explore.presentation.component.ExploreSuccessDialogContent
import com.teamoffroad.feature.explore.presentation.model.ExploreResultState
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreCameraUiStateHandler(
    uiState: ExploreUiState,
    updateExploreCameraUiState: (ExploreResultState) -> Unit,
    qrResultImageUrl: String?,
    viewModel: ExploreViewModel,
    locationResultImageUrl: String,
    navigateToHome: (String) -> Unit,
) {
    val imageUrl = locationResultImageUrl.ifBlank {
        qrResultImageUrl
    }
    when (uiState.authResultType) {
        ExploreResultState.LocationError -> {
            ExploreResultDialog(
                errorType = ExploreResultState.LocationError,
                text = stringResource(R.string.explore_location_failed_label),
                content = {
                    ExploreFailedDialogContent(
                        imageUrl = stringResource(R.string.explore_failed_img)
                    )
                },
                onDismissRequest = { updateExploreCameraUiState(ExploreResultState.None) }
            )
        }

        ExploreResultState.CodeError -> {
            ExploreResultDialog(
                errorType = ExploreResultState.CodeError,
                text = stringResource(R.string.explore_code_failed_label),
                content = { ExploreFailedDialogContent(imageUrl = imageUrl) },
                onDismissRequest = { updateExploreCameraUiState(ExploreResultState.None) }
            )
        }

        ExploreResultState.EtcError -> {
            ExploreResultDialog(
                errorType = ExploreResultState.EtcError,
                text = stringResource(R.string.explore_etc_failed_label),
                content = { ExploreFailedDialogContent(imageUrl = stringResource(R.string.explore_failed_img)) },
                onDismissRequest = { updateExploreCameraUiState(ExploreResultState.None) }
            )
        }

        ExploreResultState.Success -> {
            val category = viewModel.category.collectAsStateWithLifecycle().value

            ExploreResultDialog(
                errorType = ExploreResultState.Success,
                text = stringResource(R.string.explore_dialog_success),
                content = { ExploreSuccessDialogContent(url = imageUrl) },
                onDismissRequest = {
                    updateExploreCameraUiState(ExploreResultState.None)
                    navigateToHome(category)
                }
            )
        }

        else -> {}
    }
}