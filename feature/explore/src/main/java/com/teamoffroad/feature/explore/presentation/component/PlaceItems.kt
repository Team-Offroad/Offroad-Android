package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.feature.explore.presentation.model.FakePlaceModel

@Composable
fun PlaceItems(places: List<FakePlaceModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Black) // TODO: 색상 변경
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 18.dp),
    ) {
        items(places.size) { index ->
            ExpandableItem(
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
    }
}
