package com.teamoffroad.feature.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.translationMatrix
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.main.R

@Composable
fun AppUpdateDialog(
    onDismissRequest: () -> Unit,
    shape: Shape = RoundedCornerShape(14.dp),
    textColor: Color = Main2,
    backgroundColor: Color = Main3,
    context: Context,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val checkIfUpdateAvailable = remember { mutableStateOf(true) }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = shape
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(vertical = 20.dp, horizontal = 30.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = textColor,
                    style = OffroadTheme.typography.title,
                    text = stringResource(id = R.string.main_update_info)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp),
                    color = textColor,
                    style = OffroadTheme.typography.textRegular,
                    textAlign = TextAlign.Center,
                    text = stringResource(id = if (checkIfUpdateAvailable.value) R.string.main_update_description else R.string.main_update_unavailable)
                )
                Text(
                    text = stringResource(id = R.string.main_update),
                    color = White,
                    textAlign = TextAlign.Center,
                    style = OffroadTheme.typography.textRegular,
                    modifier = Modifier
                        .background(
                            color = Main2,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(vertical = 12.dp)
                        .clickableWithoutRipple(
                            interactionSource = interactionSource,
                            onClick = {
                                navigateToPlayStore(
                                    context = context,
                                    checkIfUpdateAvailable = checkIfUpdateAvailable
                                )
                            }
                        )
                        .fillMaxWidth(),
                )
            }
        }
    }
}

private fun navigateToPlayStore(context: Context, checkIfUpdateAvailable: MutableState<Boolean>) {
    val intent =
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=com.teamoffroad.offroad.app")
        )
            .apply { addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK) }

    if (intent.resolveActivity(context.packageManager) != null) {
        startActivity(context, intent, null)
    } else {
        checkIfUpdateAvailable.value = false
    }
}
