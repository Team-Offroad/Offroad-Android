package com.teamoffroad.feature.home.presentation.component

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.feature.home.presentation.component.download.downloadImage
import com.teamoffroad.feature.home.presentation.component.upload.uploadImage
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeIcons(
    context: Context,
    imageUrl: String,
    navigateToGainedCharacter: () -> Unit,
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
        modifier = Modifier.aspectRatio(48f / 144f).padding(top = 80.dp, end = 20.dp)
    ) {
        Column {
            val downloadInteractionSource = remember { MutableInteractionSource() }
            Image(
                painter = painterResource(id = R.drawable.ic_home_chat),
                contentDescription = "chat",
                modifier = Modifier
//                modifier = Modifier
//                    .clickableWithoutRipple(downloadInteractionSource) {
//                        if (ContextCompat.checkSelfPermission(
//                                context,
//                                permission
//                            ) == PackageManager.PERMISSION_GRANTED
//                        ) {
//                            downloadImage(context, imageUrl, scope)
//                            Toast.makeText(context, "이미지 다운 완료", Toast.LENGTH_SHORT).show()
//                        } else {
//                            launcher.launch(permission)
//                        }
//                    }
            )

            val uploadInteractionSource = remember { MutableInteractionSource() }
            Image(
                painter = painterResource(id = R.drawable.ic_home_upload),
                contentDescription = "upload",
                modifier = Modifier
                    .clickableWithoutRipple(uploadInteractionSource) {
                        val allPermissionsGranted = permissions.all {
                            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        }
                        if (allPermissionsGranted) {
                            scope.launch {
                                uploadImage(context, imageUrl)
                            }
                        } else {
                            launcher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
                        }
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_home_change),
                contentDescription = "change",
                modifier = Modifier.clickableWithoutRipple { navigateToGainedCharacter() }
            )
        }
    }
}
