package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun DiaryTimeDialog(
    title: String,
    content: String,
    cancelButtonText: String,
    nextButtonText: String,
    shape: Shape = RoundedCornerShape(14.dp),
    onClick: () -> Unit,
    onCancelClick: () -> Unit,
    isNext: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = { onCancelClick() },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = true)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(shape = shape, color = Main3),
        ) {
            Column(
                modifier = modifier
                    .padding(vertical = 22.dp, horizontal = 40.dp)
            ) {
                when (isNext) {
                    true -> {
                        Text(
                            text = title,
                            color = Main2,
                            style = OffroadTheme.typography.title,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 20.dp)
                        )
                        Text(
                            text = content,
                            color = Main2,
                            style = OffroadTheme.typography.textRegular,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .height(42.dp)
                        )
                    }

                    false -> {
                        Text(
                            text = title,
                            color = Main2,
                            style = OffroadTheme.typography.textRegular,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp, bottom = 6.dp)
                        )
                        Text(
                            text = content,
                            color = Main2,
                            style = OffroadTheme.typography.textRegular,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .height(42.dp)
                                .padding(bottom = 10.dp)
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LogoutButton(
                        text = cancelButtonText,
                        textColor = Main2,
                        backgroundColor = Main3,
                        modifier = Modifier
                            .clickableWithoutRipple { onCancelClick() }
                            .weight(1f),
                    )
                    LogoutButton(
                        text = nextButtonText,
                        textColor = White,
                        backgroundColor = Main2,
                        modifier = Modifier
                            .clickableWithoutRipple {
                                onClick()
                                onCancelClick()
                            }
                            .weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun LogoutButton(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
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