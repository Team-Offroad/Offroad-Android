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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.teamoffroad.feature.home.presentation.component.download.downloadImage
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeIcons(
    context: Context,
    url: String
) {
//    val scope = rememberCoroutineScope()
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        if (permissions.values.all { it }) {
//            downloadImage(context, url, scope)
//        } else {
//            Toast.makeText(context, "이미지를 다운로드하려면 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
//        }
//    }

    val scope = rememberCoroutineScope()
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) {
            downloadImage(context = context, url = url, scope = scope)
        } else {
            Toast.makeText(context, "이미지를 다운로드하려면 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(top = 64.dp, end = 28.dp)
    ) {
        Column {
            IconButton(onClick = {
                // 권한 요청
                val hasPermissions = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED

                if (!hasPermissions) {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_MEDIA_IMAGES
                        )
                    )
                } else {
                    downloadImage(context, url, scope)
                }
            })  {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_download),
                    contentDescription = "download",
                )
            }

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

