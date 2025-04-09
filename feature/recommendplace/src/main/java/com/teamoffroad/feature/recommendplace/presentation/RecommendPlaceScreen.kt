package com.teamoffroad.feature.recommendplace.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceBody
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceHeader
import com.teamoffroad.offroad.feature.recommendplace.R


@Composable
fun RecommendPlaceScreen(
    navigateToBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .navigationPadding()
            .background(color = Main1)
            .actionBarPadding()
    ) {
        NavigateBackAppBar(
            text = stringResource(id = R.string.recommend_place_back_to_home),
            modifier = Modifier.padding(top = 20.dp)
        ) { navigateToBack() }
        RecommendPlaceHeader()
        RecommendPlaceBody()
    }
}