package com.teamoffroad.feature.home.presentation.component.dialog

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.offroad.feature.home.R

@Composable
fun CloseDialog(
    onClickCancel: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            onClick = {
                onClickCancel()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_close),
                contentDescription = "close",
                modifier = Modifier.padding(top = 6.dp, end = 4.dp),
            )
        }
    }
}