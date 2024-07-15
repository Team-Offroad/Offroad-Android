package com.teamoffroad.feature.home.presentation.component

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
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
    val scope = rememberCoroutineScope()

//        val permissions = listOf(
//        Manifest.permission.READ_MEDIA_IMAGES, //
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//    )

    val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }


    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(top = 64.dp, end = 28.dp)
    ) {
        Column {
            IconButton(onClick = {
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Permission Already Granted", Toast.LENGTH_SHORT).show()
                    downloadImage(context, url, scope)
                } else {
                    launcher.launch(permission)
                }
            }) {
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
