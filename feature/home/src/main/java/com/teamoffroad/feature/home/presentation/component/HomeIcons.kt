package com.teamoffroad.feature.home.presentation.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.teamoffroad.characterchat.presentation.model.CharacterChatLastUnreadUiState
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.ErrorNew
import com.teamoffroad.feature.home.presentation.component.upload.uploadImage
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeIcons(
    context: Context,
    imageUrl: String,
    characterName: String,
    characterChatLastUnreadUiState: State<CharacterChatLastUnreadUiState>,
    navigateToGainedCharacter: () -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateCharacterName: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        scope.launch {
            val deniedPermissions = permissions.filterValues { !it }.keys
            if (deniedPermissions.isEmpty()) {
                showToast(context, context.getString(R.string.allowed_permissions))
            } else {
                showToast(
                    context, context.getString(R.string.not_allowed_permissions)
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 20.dp)
                .width(48.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_chat),
                    contentDescription = "chat",
                    modifier = Modifier
                        .clickableWithoutRipple {
                            if (characterChatLastUnreadUiState.value.doesAllRead) {
                                updateCharacterName(characterName)
                                updateShowUserChatTextField(true)
                            } else {
                                updateCharacterChatExist(true) // 마지막 채팅 내려오기
                                // 읽음 처리하기
                            }
                        }
                )

                showCharacterChatExist(characterChatLastUnreadUiState)
            }

            val uploadInteractionSource = remember { MutableInteractionSource() }
            Image(
                painter = painterResource(id = R.drawable.ic_home_upload),
                contentDescription = "upload",
                modifier = Modifier
                    .clickableWithoutRipple(interactionSource = uploadInteractionSource) {
                        val allPermissionsGranted =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                permissions.all {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.READ_MEDIA_IMAGES
                                    ) == PackageManager.PERMISSION_GRANTED
                                }
                            } else {
                                permissions.all {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        it
                                    ) == PackageManager.PERMISSION_GRANTED
                                }
                            }

                        if (allPermissionsGranted) {
                            scope.launch {
                                uploadImage(context, imageUrl)
                            }
                        } else {
                            launcher.launch(
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
                                } else {
                                    arrayOf(
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                    )
                                }
                            )
                        }

                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_home_change_character),
                contentDescription = "change",
                modifier = Modifier.clickableWithoutRipple { navigateToGainedCharacter() }
            )
        }
    }
}

@Composable
private fun showCharacterChatExist(
    characterChatLastUnreadUiState: State<CharacterChatLastUnreadUiState>,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Canvas(
            modifier = Modifier
                .padding(top = 6.dp, end = 6.dp)
                .size(if (!characterChatLastUnreadUiState.value.doesAllRead) 8.dp else 0.dp)
        ) {
            drawCircle(
                color = ErrorNew,
                style = Fill
            )
        }
    }
}

suspend fun showToast(context: Context, message: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}