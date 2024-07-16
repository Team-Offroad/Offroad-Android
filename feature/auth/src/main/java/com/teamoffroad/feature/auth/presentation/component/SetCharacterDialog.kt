package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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