package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.teamoffroad.offroad.feature.diary.R

@Composable
fun TimeSettingDialog(
    shape: Shape = RoundedCornerShape(14.dp),
    onClick: () -> Unit,
    onSettingClick: (Boolean) -> Unit,
    navigateDiaryTime: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = { onSettingClick(false) },
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
                    text = stringResource(id = R.string.diary_time_setting_title),
                    color = Main2,
                    style = OffroadTheme.typography.title,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = stringResource(id = R.string.diary_time_setting_content),
                    color = Main2,
                    style = OffroadTheme.typography.textRegular,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 22.dp),
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 22.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.diary_time_setting),
                        color = Gray400,
                        style = OffroadTheme.typography.textRegular.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(end = 2.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.diary_time_setting_description),
                        color = Gray400,
                        style = OffroadTheme.typography.textRegular,
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LogoutButton(
                        text = stringResource(id = R.string.diary_time_setting),
                        textColor = Main2,
                        backgroundColor = Main3,
                        modifier = Modifier
                            .clickableWithoutRipple {
                                onSettingClick(false)
                                navigateDiaryTime()
                            }
                            .weight(1f),
                    )
                    LogoutButton(
                        text = stringResource(id = R.string.diary_time_success),
                        textColor = White,
                        backgroundColor = Main2,
                        modifier = Modifier
                            .clickableWithoutRipple {
                                onClick()
                                onSettingClick(false)
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