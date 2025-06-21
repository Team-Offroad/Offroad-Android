package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState.RecommendationsUiState

@Composable
fun RecommendPlaceItems(
    places: List<RecommendationsUiState>,
    isLoading: Boolean,
    isLoadable: Boolean,
    isAdditionalLoading: Boolean,
    updatePlaces: () -> Unit,
    listState: LazyListState
) {
    var expandedIndex by remember { mutableIntStateOf(NULL_INDEX) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        state = listState,
        contentPadding = PaddingValues(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = when (isLoading) {
            true -> Arrangement.Center
            false -> Arrangement.Top
        },
    ) {
        items(places.size) { index ->
            ExpandableItem(
                isExpanded = expandedIndex == index,
                onExpandClick = {
                    expandedIndex = if (expandedIndex == index) NULL_INDEX else index
                },
                defaultContent = {
                    RecommendPlaceItem(
                        recommendPlaceModel = places[index],
                        isMap = true
                    )
                },
                extraContent = {
                    RecommendPlaceExtraItem(
                        recommendPlaceModel = places[index]
                    )
                },
                modifier = Modifier.padding(bottom = 14.dp)
            )
        }
    }
}

private const val NULL_INDEX = -1
private const val LOAD_THRESHOLD = 10
