package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.offroad.feature.home.R

@Composable
fun HomeIcons() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(top = 64.dp, end = 28.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_home_download),
                contentDescription = "explorer",
            )
            Image(
                painter = painterResource(id = R.drawable.ic_home_upload),
                contentDescription = "upload",
            )
            Image(
                painter = painterResource(id = R.drawable.ic_home_change),
                contentDescription = "change",
            )
        }
    }
}
