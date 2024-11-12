package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.explore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreAppBar(
    topPadding: Int,
) {
    Column {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = Main1,
                titleContentColor = Main2,
            ),
            title = {
                Text(
                    stringResource(R.string.explore_title),
                    textAlign = TextAlign.Start,
                    style = OffroadTheme.typography.subtitle2Semibold,
                    modifier = Modifier.padding(top = 12.dp),
                )
            },
            modifier = Modifier.height(topPadding.dp),
        )
        HorizontalDivider(
            color = Gray100,
            modifier = Modifier.height(1.dp),
        )
    }
}
