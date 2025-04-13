package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.ErrorNew
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrderLocation(
    locationText: String,
    showWarning: Boolean,
    onLocationTextChanged: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 34.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recommend_place_order_question_location),
            style = OffroadTheme.typography.textBold,
            color = Main2
        )

        BasicTextField(
            value = locationText,
            onValueChange = onLocationTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(Main3, RoundedCornerShape(6.dp))
                .border(1.dp, if (isFocused) Main2 else Gray100, RoundedCornerShape(6.dp))
                .padding(horizontal = 12.dp, vertical = 14.dp),
            cursorBrush = SolidColor(Main2),
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Box {
                    if (locationText.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.recommend_place_order_question_location_placeholder),
                            style = OffroadTheme.typography.textAuto,
                            color = Gray300
                        )
                    }
                    innerTextField()
                }
            }
        )

        if (showWarning) {
            Text(
                text = stringResource(id = R.string.recommend_place_order_question_location_warning),
                color = ErrorNew,
                style = OffroadTheme.typography.hint,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}
