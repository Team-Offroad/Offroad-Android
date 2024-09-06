package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.feature.explore.presentation.model.FakePlaceModel
import com.teamoffroad.offroad.feature.explore.R
import kotlinx.coroutines.launch

@Composable
fun PlaceViewPager() {
    val tabTitles = listOf(
        stringResource(R.string.explore_unvisited_place),
        stringResource(R.string.explore_total_place),
    )
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Main1,
            contentColor = Main2,
            divider = {},
            indicator = { tabPositions ->
                val currentTabPosition = tabPositions[pagerState.currentPage]
                Box(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.BottomStart)
                        .offset(x = currentTabPosition.left)
                        .width(currentTabPosition.width)
                        .height(3.dp)
                        .background(Sub)
                )
            },
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            style = OffroadTheme.typography.tooltipTitle,
                        )
                    },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = Main2,
                    unselectedContentColor = Gray100,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = index,
                                animationSpec = tween(durationMillis = 200)
                            )
                        }
                    }
                )
            }
        }
        HorizontalDivider(
            color = Gray100,
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            val dummyPlaces = FakePlaceModel.dummyPlaces
            when (page) {
                0 -> PlaceItems(dummyPlaces)
                1 -> PlaceItems(dummyPlaces)
            }
        }
    }
}
