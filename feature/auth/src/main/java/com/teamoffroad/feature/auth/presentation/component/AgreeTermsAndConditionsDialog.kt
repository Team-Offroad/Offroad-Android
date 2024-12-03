package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.auth.R

@Composable
fun AgreeTermsAndConditionsDialog(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(14.dp),
    onAgreeClick: () -> Unit,
    onDisAgreeClick: () -> Unit,
    onClickCancel: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = true)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(shape = shape, color = Main3),
        ) {
            Column(
                modifier = modifier
                    .padding(vertical = 32.dp)
                    .padding(start = 40.dp, end = 28.dp)
            ) {
                Text(
                    text = title,
                    color = Main2,
                    style = OffroadTheme.typography.title,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = content,
                    color = Gray400,
                    style = OffroadTheme.typography.marketing,
                    modifier = Modifier
                        .height(290.dp)
                        .padding(end = 12.dp)
                        .verticalScroll(scrollState)
                        .verticalScrollbar(scrollState)
                )
                Spacer(modifier = Modifier.padding(bottom = 30.dp))
                Row(
                    modifier = Modifier.padding(end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AgreeButton(
                        modifier = Modifier
                            .weight(1f)
                            .clickableWithoutRipple {
                                onClickCancel()
                                onDisAgreeClick()
                            },
                        text = stringResource(R.string.auth_agree_and_terms_conditions_dialog_disagree),
                        textColor = Main2,
                        backgroundColor = Main3,
                    )
                    AgreeButton(
                        modifier = Modifier
                            .weight(1f)
                            .clickableWithoutRipple {
                                onClickCancel()
                                onAgreeClick()
                            },
                        text = stringResource(R.string.auth_agree_and_terms_conditions_dialog_agree),
                        textColor = White,
                        backgroundColor = Main2,
                    )
                }
            }
        }
    }
}

@Composable
private fun AgreeButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(
        text = text,
        color = textColor,
        style = OffroadTheme.typography.btnSmall,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp)
            )
            .border(width = 1.dp, shape = RoundedCornerShape(5.dp), color = Main2)
            .padding(vertical = 14.dp, horizontal = 38.dp)
    )
}