package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.explore.presentation.PlaceViewModel
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceBody(

) {
    var selectedTab by remember { mutableStateOf(RecommendTab.LIST) }
    var isButtonVisible by remember { mutableStateOf(true) }

    // 스크롤 상태 공유
    val listState = rememberLazyListState()

    // 이전 스크롤 위치 저장
    var previousScrollOffset by remember { mutableStateOf(0) }

    // 스크롤 감지
    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        isButtonVisible = previousScrollOffset >= currentOffset || listState.firstVisibleItemIndex == 0
        previousScrollOffset = currentOffset
    }

    Column {
        AnimatedVisibility(visible = isButtonVisible) {
            RecommendPlaceButton()
        }
        RecommendPlaceBodyTabs(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )
        HorizontalDivider(color = Gray100)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ListBg)
        ) {
            when (selectedTab) {
                RecommendTab.LIST -> RecommendPlaceList(listState)
                RecommendTab.MAP -> RecommendPlaceMap()
            }
        }
    }
}

enum class RecommendTab {
    LIST, MAP
}

@Composable
fun RecommendPlaceList(listState: LazyListState) {
    val placeViewModel: PlaceViewModel = hiltViewModel()
    val uiState = placeViewModel.uiState.collectAsStateWithLifecycle().value

    RecommendPlaceItems(
        places = uiState.visitedPlaces + uiState.unvisitedPlaces,
        isLoading = uiState.isLoading,
        isLoadable = uiState.isLoadable,
        isAdditionalLoading = uiState.isAdditionalLoading,
        updatePlaces = placeViewModel::updatePlaces,
        listState = listState
    )
}

@Composable
fun RecommendPlaceMap() {
    Text(text = "map")
}

@Composable
fun RecommendPlaceBodyTabs(
    selectedTab: RecommendTab,
    onTabSelected: (RecommendTab) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 22.dp, bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.recommend_place),
            style = OffroadTheme.typography.textContents
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.recommend_place_list),
            style = if (selectedTab == RecommendTab.LIST)
                OffroadTheme.typography.tooltipNumber
            else
                OffroadTheme.typography.textContentsSmall,
            modifier = Modifier
                .clickableWithoutRipple { onTabSelected(RecommendTab.LIST) }
                .padding(end = 10.dp),
            color = if (selectedTab == RecommendTab.LIST) Main2 else Gray300
        )
        Box(
            modifier = Modifier
                .height(10.dp)
                .width(1.dp)
                .background(Main2)
        )
        Text(
            text = stringResource(id = R.string.recommend_place_map),
            style = if (selectedTab == RecommendTab.MAP)
                OffroadTheme.typography.tooltipNumber
            else
                OffroadTheme.typography.textContentsSmall,
            modifier = Modifier
                .clickableWithoutRipple { onTabSelected(RecommendTab.MAP) }
                .padding(start = 10.dp),
            color = if (selectedTab == RecommendTab.MAP) Main2 else Gray300
        )
    }
}
