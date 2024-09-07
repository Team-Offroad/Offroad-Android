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
    navigateToGainedCharacter: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "권한이 허용되지 않았습니다.", Toast.LENGTH_SHORT).show()
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
                        if (ContextCompat.checkSelfPermission(
                                context,
                                permission
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            downloadImage(context, imageUrl, scope)
                            Toast.makeText(context, "이미지 다운 완료", Toast.LENGTH_SHORT).show()
                        } else {
                            launcher.launch(permission)
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
                modifier = Modifier.clickableWithoutRipple { navigateToGainedCharacter() }
            )
        }
    }
}
