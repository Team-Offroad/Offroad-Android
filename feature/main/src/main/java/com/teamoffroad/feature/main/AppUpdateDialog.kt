package com.teamoffroad.feature.main

import android.app.Dialog
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Dialog

@Composable
fun AppUpdateDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    shape: Shape = RoundedCornerShape(14.dp),
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(218.dp),
            shape = shape
        ) {
            Text(text = "dialog")
        }
    }
}