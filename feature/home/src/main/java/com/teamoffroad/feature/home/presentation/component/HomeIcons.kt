package com.teamoffroad.feature.home.presentation.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
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
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeIcons(
    context: Context,
    imageUrl: String,
    characterName: String,
    newDiaryExist: Boolean,
    characterChatLastUnreadUiState: State<CharacterChatLastUnreadUiState>,
    navigateToGainedCharacter: () -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateCharacterName: (String) -> Unit,
    updateLastUnreadChatDosAllRead: (Boolean) -> Unit,
    navigateToDiary: (Boolean) -> Unit,
    navigateToRecommendPlace: (Boolean, String) -> Unit,
) {
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
                                updateLastUnreadChatDosAllRead(true) // 읽음 처리
                            }
                        }
                )

                showCharacterChatExist(characterChatLastUnreadUiState)
            }

            Box(
                modifier = Modifier.clickableWithoutRipple { navigateToRecommendPlace(false, "") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_recommend_place),
                    contentDescription = "recommend place",
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_home_change_character),
                contentDescription = "change",
                modifier = Modifier.clickableWithoutRipple { navigateToGainedCharacter() }
            )

            Box(
                modifier = Modifier.clickableWithoutRipple { navigateToDiary(!newDiaryExist) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_diary_empty),
                    contentDescription = "empty_diary",
                )
                if (!newDiaryExist) {
                    Canvas(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 6.dp, end = 6.dp)
                            .size(8.dp)
                    ) {
                        drawCircle(
                            color = ErrorNew,
                            style = Fill
                        )
                    }
                }
            }
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