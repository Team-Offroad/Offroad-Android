package com.teamoffroad.feature.home.presentation.component.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.NametagStroke
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.model.DummyDataModel
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
                    CharacterTitle(LocalContext.current)
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
fun CharacterTitle(context: Context) {
    val characterTitles = remember {
        mutableStateListOf(
            DummyDataModel("오프로드 스타터", 0),
            DummyDataModel("위대한 첫 걸음", 1),
            DummyDataModel("왕초보 탐험가", 2),
            DummyDataModel("초보 모험가", 3),
            DummyDataModel("오프로드 스타터", 4),
            DummyDataModel("위대한 첫 걸음", 5),
            DummyDataModel("왕초보 탐험가", 6),
            DummyDataModel("초보 모험가", 7),
            DummyDataModel("오프로드 스타터", 8),
            DummyDataModel("위대한 첫 걸음", 9),
            DummyDataModel("왕초보 탐험가", 10),
            DummyDataModel("초보 모험가", 11)
        )
    }
    val selectedDummyDataModel = remember { mutableStateOf<DummyDataModel?>(null) }
    val dummyDataState = rememberLazyListState()

    Box {
        LazyColumn(
            state = dummyDataState,
            modifier = Modifier
                .heightIn(0.dp, 246.dp)
                .drawScrollbar(dummyDataState),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(characterTitles) { data ->
                DialogTagItem(
                    text = data.title,
                    textColor = if (data == selectedDummyDataModel.value) White else Main2,
                    style = OffroadTheme.typography.subtitle2Semibold,
                    backgroundColor = if (data == selectedDummyDataModel.value) Sub else NametagInactive,
                    borderColor = if (data == selectedDummyDataModel.value) Sub else NametagStroke,
                    dummyDataModel = data,
                    onItemClick = { clickedData ->
                        selectedDummyDataModel.value = clickedData
                        Toast.makeText(context, data.idx.toString(), Toast.LENGTH_SHORT).show()
                    }
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
