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
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun LogoutDialog(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(14.dp),
    onClick: () -> Unit,
    onClickCancel: () -> Unit,
) {
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
                    .padding(vertical = 22.dp, horizontal = 40.dp)
            ) {
                Text(
                    text = stringResource(R.string.my_page_setting_item_logout),
                    color = Main2,
                    style = OffroadTheme.typography.title,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp)
                )
                Text(
                    text = stringResource(R.string.my_page_setting_logout_dialog_sub_title),
                    color = Main2,
                    style = OffroadTheme.typography.textRegular,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(42.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LogoutButton(
                        text = stringResource(R.string.my_page_setting_logout_dialog_disagree),
                        onClick = onClickCancel,
                        textColor = Main2,
                        backgroundColor = Main3,
                        modifier = Modifier.weight(1f)
                    )
                    LogoutButton(
                        text = stringResource(R.string.my_page_setting_logout_dialog_agree),
                        onClick = {
                            onClick()
                            onClickCancel()
                        },
                        textColor = White,
                        backgroundColor = Main2,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun LogoutButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(text = text,
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
            .clickableWithoutRipple { onClick() })
}