package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrderDialog(
    navigateToBack: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            onClickCancel()
        },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.aspectRatio(310f / 166f),
            colors = CardDefaults.cardColors(containerColor = Main3)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.recommend_place_order_not_safe_info),
                    style = OffroadTheme.typography.textRegular,
                    color = Main2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 30.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .padding(top = 22.dp, bottom = 30.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.recommend_place_order_cancel),
                        textAlign = TextAlign.Center,
                        style = OffroadTheme.typography.btnSmall,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 6.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(6.dp),
                                color = Main2
                            )
                            .padding(horizontal = 44.dp, vertical = 14.dp)
                            .clickableWithoutRipple { onClickCancel() }
                    )
                    Text(
                        text = stringResource(id = R.string.recommend_place_order_confirm),
                        textAlign = TextAlign.Center,
                        style = OffroadTheme.typography.btnSmall,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 6.dp)
                            .background(
                                shape = RoundedCornerShape(6.dp),
                                color = Main2
                            )
                            .padding(horizontal = 44.dp, vertical = 14.dp)
                            .clickableWithoutRipple { navigateToBack() },
                        color = White
                    )
                }
            }
        }
    }
}