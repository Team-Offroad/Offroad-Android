package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceSelect() {
    var selected by remember { mutableStateOf<PlaceType?>(null) }

    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 34.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recommend_place_order_question_select),
            style = OffroadTheme.typography.textBold,
            color = Main2
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            PlaceType.entries.forEach { type ->
                val isSelected = selected == type
                Text(
                    text = stringResource(id = type.textRes),
                    textAlign = TextAlign.Center,
                    style = OffroadTheme.typography.btnSmall,
                    color = if (isSelected) Main3 else Gray300,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .border(
                            shape = RoundedCornerShape(30.dp),
                            color = if (isSelected) Sub else Gray300,
                            width = 1.dp
                        )
                        .background(
                            shape = RoundedCornerShape(30.dp),
                            color = if (isSelected) Sub else Main3
                        )
                        .clickableWithoutRipple { selected = type }
                        .padding(horizontal = 64.dp, vertical = 12.dp),
                )
            }
        }
    }
}

enum class PlaceType(@StringRes val textRes: Int) {
    RESTAURANT(R.string.recommend_place_order_question_restaurant),
    CAFE(R.string.recommend_place_order_question_cafe)
}