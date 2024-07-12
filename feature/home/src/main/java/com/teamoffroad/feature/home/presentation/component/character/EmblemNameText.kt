package com.teamoffroad.feature.home.presentation.component.character

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.feature.home.presentation.component.dialog.OffroadDialog
import com.teamoffroad.core.designsystem.component.OffroadTagItem
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.component.dialog.CustomAlertDialogState
import com.teamoffroad.offroad.feature.home.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun EmblemNameText(
    modifier: Modifier = Modifier
) {
    val customAlertDialogState: MutableState<CustomAlertDialogState> =
        mutableStateOf(CustomAlertDialogState())
    val showDialog = mutableStateOf(false)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            OffroadTagItem(
                text = "초보 모험가",
                textColor = White,
                style = OffroadTheme.typography.subtitle2Semibold,
                backgroundColor = Sub
            )
            IconButton(
                onClick = {
                    showDialog.value = true
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_change_title),
                    contentDescription = "change title",
                    modifier = Modifier
                        .padding(end = 20.dp)
                )

                if (showDialog.value) {
                    OffroadDialog(
                        onClickCancel = {
                            showDialog.value = false
                            customAlertDialogState.value.onClickCancel()
                        }
                    )
                }
            }
        }
    }
}
