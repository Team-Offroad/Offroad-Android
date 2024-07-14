package com.teamoffroad.feature.explore.presentation

import android.content.Context
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.naver.maps.map.overlay.InfoWindow
import com.teamoffroad.feature.explore.presentation.component.CustomInfoWindow
import com.teamoffroad.feature.explore.presentation.model.PlaceModel

class ComposeInfoWindowAdapter(
    private val context: Context,
    private val place: PlaceModel,
) : InfoWindow.ViewAdapter() {

    @Composable
    fun CustomInfoWindowComposable(
        title: String,
        shortIntroduction: String,
        address: String,
        visitCount: Int,
        categoryImage: String,
        onButtonClick: () -> Unit,
    ) {
        CustomInfoWindow(
            title = title,
            shortIntroduction = shortIntroduction,
            address = address,
            visitCount = visitCount,
            onButtonClick = onButtonClick,
            modifier = Modifier,
            categoryImage = categoryImage,
        )
    }

    override fun getView(infoWindow: InfoWindow): View {
        val composeView = ComposeView(context).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                CustomInfoWindowComposable(
                    title = place.name,
                    shortIntroduction = place.shortIntroduction,
                    address = place.address,
                    visitCount = place.visitCount,
                    categoryImage = place.categoryImage,
                    onButtonClick = {
                        // TODO: Button click action
                    },
                )
            }
        }
        return composeView
    }
}