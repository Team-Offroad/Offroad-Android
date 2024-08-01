package com.teamoffroad.feature.home.presentation.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.feature.home.presentation.component.download.downloadImage
import com.teamoffroad.offroad.feature.home.R

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeIcons(
    context: Context,
    imageUrl: String,
    category: String
) {
    val scope = rememberCoroutineScope()

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) {
            Toast.makeText(context, context.getString(R.string.allowed_permissions), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, context.getString(R.string.not_allowed_permissions), Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(top = 64.dp, end = 28.dp)
    ) {
        Column {
            val downloadInteractionSource = remember { MutableInteractionSource() }
            Image(
                painter = painterResource(id = R.drawable.ic_home_download),
                contentDescription = "download",
                modifier = Modifier
                    .clickableWithoutRipple(downloadInteractionSource) {
                        val allPermissionsGranted = permissions.all {
                            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                        }
                        if (allPermissionsGranted) {
                            downloadImage(context, imageUrl, scope)
                        } else {
                            launcher.launch(permissions.toTypedArray())
                        }
                    }
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
