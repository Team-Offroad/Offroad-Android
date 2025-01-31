package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.explore.presentation.component.PlaceHeader
import com.teamoffroad.feature.explore.presentation.component.PlaceViewPager
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun PlaceScreen(
    latitude: String,
    longitude: String,
    navigateToBack: () -> Unit,
    placeViewModel: PlaceViewModel = hiltViewModel(),
) {
    val uiState = placeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        placeViewModel.updatePosition(latitude.toDouble(), longitude.toDouble())
    }

    Column(
        modifier = Modifier
            .navigationPadding()
            .background(color = Main1)
            .actionBarPadding()
    ) {
        NavigateBackAppBar(
            text = stringResource(id = R.string.explore_explore),
            modifier = Modifier.padding(top = 20.dp)
        ) { navigateToBack() }
        PlaceHeader()
        PlaceViewPager(uiState.value, placeViewModel::updatePlaces)
    }
}
