package com.teamoffroad.feature.explore.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun PlaceScreen(
    navigateToExplore: (String, String) -> Unit,
) {
    OffroadActionBar()
    NavigateBackAppBar { navigateToExplore("", "") }
}

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    OffroadTheme {
        PlaceScreen(navigateToExplore = { _, _ -> })
    }
}
