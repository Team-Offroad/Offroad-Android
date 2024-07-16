package com.teamoffroad.feature.home.presentation.component.character

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.OffroadTagItem
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.CharacterName
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.component.dialog.OffroadDialog
import com.teamoffroad.feature.home.presentation.model.CustomTitleDialogStateModel
import com.teamoffroad.offroad.feature.home.R

class CharacterItem {

    @Composable
    fun CharacterImage() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_home_explorer),
                contentDescription = "explorer"
            )
        }
    }

    @Composable
    fun CharacterNameText(name: String) {
        Text(
            style = OffroadTheme.typography.subtitle2Semibold,
            text = name,
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp)
                .background(
                    color = CharacterName,
                    shape = RoundedCornerShape(6.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = Black15
                )
                .padding(horizontal = 16.dp)
                .padding(vertical = 6.dp),
            color = White
        )
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun EmblemNameText(
        modifier: Modifier = Modifier
    ) {
        val customTitleDialogStateModel = remember { mutableStateOf<CustomTitleDialogStateModel?>(null) }

        val showDialog = mutableStateOf(false)
        var selectedCharacterIdx = remember { mutableStateOf<String?>("") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                OffroadTagItem(
                    text = selectedCharacterIdx.value.toString(),
                    textColor = White,
                    style = OffroadTheme.typography.subtitle2Semibold,
                    backgroundColor = Sub
                )
                IconButton(
                    onClick = {
                        showDialog.value = true
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_home_change_title),
                        contentDescription = "change title",
                    )
                    if (showDialog.value) {
                        OffroadDialog(
                            showDialog,
                            customTitleDialogStateModel,
                            onClickCancel = {
                                showDialog.value = false
                                customTitleDialogStateModel.value?.onClickCancel
                            },
                            onCharacterChange = { idx ->
                                selectedCharacterIdx.value = idx.toString()
                            }
                        )
                    }
                }
            }
        }
    }
}