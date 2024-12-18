package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.CircularLoadingAnimationLine
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.core.designsystem.component.LinearLoadingAnimation
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.explore.presentation.model.PlaceModel

@Composable
fun PlaceItems(
    places: List<PlaceModel>,
    isLoading: Boolean,
    isLoadable: Boolean,
    isAdditionalLoading: Boolean,
    updatePlaces: () -> Unit,
) {
    var expandedIndex by remember { mutableIntStateOf(NULL_INDEX) }
    val listState = rememberLazyListState()

    LaunchedEffect(listState, places, isLoadable) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index + LOAD_THRESHOLD >= places.size && isLoadable) {
                    updatePlaces()
                }
            }
    }

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
        item {
            LinearLoadingAnimation(isLoading = isLoading)
        }
        items(places.size) { index ->
            ExpandableItem(
                isExpanded = expandedIndex == index,
                onExpandClick = {
                    expandedIndex = if (expandedIndex == index) NULL_INDEX else index
                },
                defaultContent = {
                    PlaceItem(
                        placeModel = places[index]
                    )
                },
                extraContent = {
                    PlaceExtraItem(
                        placeModel = places[index]
                    )
                },
                modifier = Modifier.padding(bottom = 14.dp)
            )
        }
        item {
            CircularLoadingAnimationLine(isLoading = isAdditionalLoading)
        }
    }
}

private const val NULL_INDEX = -1
private const val LOAD_THRESHOLD = 10
