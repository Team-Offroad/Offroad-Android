package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun SetCharacterDialog(
    characterName: String,
    showDialog: MutableState<Boolean>,
    onClickCancel: () -> Unit,
    onCharacterChange: (Int?) -> Unit,
) {
    Dialog(
        onDismissRequest = {
            onClickCancel()
        },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "$characterName}와 함께 하겠어요?",
                    style = OffroadTheme.typography.title,
                    color = Main2
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text("지금 캐릭터를 선택하시면", style = OffroadTheme.typography.textRegular, color = Main2)
                Text(
                    "${characterName}와 모험을 시작하게 돼요.",
                    style = OffroadTheme.typography.textRegular,
                    color = Main2
                )
            }
        }
    }
}

@Composable
fun SetCharacterDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 40.dp)
    ) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(dismissOnClickOutside = false),
            title = null,
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "오푸와 함께하시겠어요?",
                        style = OffroadTheme.typography.title,
                        color = Main2,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "지금 캐릭터를 선택하시면\n오푸와 모험을 시작하게 돼요.",
                        style = OffroadTheme.typography.textRegular,
                        color = Main2,
                        textAlign = TextAlign.Center
                    )
                }
            },
            dismissButton = {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth(0.48f)
                        .padding(horizontal = 6.dp)
                        .clickable { onDismissRequest() }
                        .background(color = Color.Transparent, shape = RoundedCornerShape(6.dp))
                        .border(1.dp, Main2, RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "아니요",
                        color = Main2,
                        style = OffroadTheme.typography.btnSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            },
            confirmButton = {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth(0.48f)
                        .padding(horizontal = 6.dp)
                        .clickable { onConfirm() }
                        .background(color = Main2, shape = RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "네,좋아요!",
                        color = Color.White,
                        style = OffroadTheme.typography.btnSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            containerColor = Color(0xFFF9E5D2),
        )
    }
}