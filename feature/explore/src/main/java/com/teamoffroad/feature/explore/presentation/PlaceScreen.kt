package com.teamoffroad.feature.explore.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun PlaceScreen(
    navigateToExplore: (String, String) -> Unit,
) {
    OffroadActionBar()
    NavigateBackAppBar(text = stringResource(id = R.string.explore_explore)) { navigateToExplore("", "") }
    PlaceHeader()
}

@Composable
fun PlaceHeader() {
    Text(text = "장소 목록")
}

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    OffroadTheme {
        PlaceScreen(navigateToExplore = { _, _ -> })
    }
}
