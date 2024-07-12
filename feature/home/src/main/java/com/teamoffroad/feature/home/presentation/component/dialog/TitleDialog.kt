package com.teamoffroad.feature.home.presentation.component.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.NametagStroke
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.home.R

@Composable
fun OffroadDialog(
    showDialog: MutableState<Boolean>,
    customTitleDialogState: MutableState<CustomTitleDialogState>,
    onClickCancel: () -> Unit
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
            Box {
                CloseDialog(
                    onClickCancel = {
                        showDialog.value = false
                        customTitleDialogState.value.onClickCancel()
                    }
                )
                Column(
                    modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
                ) {
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    DialogTitle()
                    Spacer(modifier = Modifier.padding(top = 22.dp))
                    CharacterTitle()
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    ChangeCharacterTitle()
                }
            }
        }
    }
}

@Composable
fun DialogTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.home_collected_character_name),
        style = OffroadTheme.typography.title,
        textAlign = TextAlign.Center
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CharacterTitle() {
    val characterTitles = mutableStateListOf("오프로드 스타터", "위대한 첫 걸음", "왕초보 탐험가", "초보 모험가",
        "오프로드 스타터", "위대한 첫 걸음", "왕초보 탐험가", "초보 모험가",
        "오프로드 스타터", "위대한 첫 걸음", "왕초보 탐험가", "초보 모험가",
        "오프로드 스타터", "위대한 첫 걸음", "왕초보 탐험가", "초보 모험가")
    val listState = rememberLazyListState()

    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .heightIn(0.dp, 246.dp)
                .drawScrollbar(listState),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(characterTitles) { title ->
                DialogTagItem(
                    text = title,
                    textColor = Main2,
                    style = OffroadTheme.typography.subtitle2Semibold,
                    backgroundColor = NametagInactive,
                    borderColor = NametagStroke
                )
            }
        }
    }
}

@Composable
fun ChangeCharacterTitle() {
    DialogChangeButton(
        text = "바꾸기",
        textColor = White,
        style = OffroadTheme.typography.textRegular,
        backgroundColor = Main2
    )
}
