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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.NametagStroke
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.UiState
import com.teamoffroad.feature.home.presentation.model.CustomTitleDialogStateModel
import com.teamoffroad.offroad.feature.home.R

@Composable
fun OffroadDialog(
    showDialog: MutableState<Boolean>,
    customTitleDialogStateModel: MutableState<CustomTitleDialogStateModel?>,
    onClickCancel: () -> Unit,
    onCharacterChange: (String?) -> Unit,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val emblemListState =
        viewModel.state.collectAsState(initial = UiState.Loading).value
    val context = LocalContext.current

    val emblem = when (emblemListState) {
        is UiState.Success -> {
            emblemListState.data
        }

        is UiState.Failure -> {
            Toast.makeText(context, emblemListState.errorMessage, Toast.LENGTH_SHORT).show()
            null
        }

        else -> null
    }


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
                        customTitleDialogStateModel.value?.onClickCancel
                    }
                )

                val selectedItem = remember { mutableStateOf<Emblem?>(null) }

                Column(
                    modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
                ) {
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    DialogTitle()
                    Spacer(modifier = Modifier.padding(top = 22.dp))
                    if (emblem != null) {
                        CharacterTitle(emblems = emblem) { itemSelected ->
                            selectedItem.value = itemSelected
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    ChangeCharacterTitle(
                        isSelected = selectedItem.value != null,
                        onClickChange = {
                            onCharacterChange(selectedItem.value?.emblemCode)
                            showDialog.value = false
                            customTitleDialogStateModel.value?.onClickCancel
                        }
                    )
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
fun CharacterTitle(
    emblems: List<Emblem>,
    onSelectionChange: (Emblem?) -> Unit
) {
    val responseEmblemData = remember { mutableStateOf<Emblem?>(null) }
    val emblemDataState = rememberLazyListState()

    Box {
        LazyColumn(
            state = emblemDataState,
            modifier = Modifier
                .heightIn(246.dp)
                .drawScrollbar(emblemDataState),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(emblems) { data ->
                DialogTagItem(
                    text = data.emblemName,
                    textColor = if (data.emblemCode == responseEmblemData.value?.emblemCode) White else Main2,
                    style = OffroadTheme.typography.subtitle2Semibold,
                    backgroundColor = if (data.emblemCode == responseEmblemData.value?.emblemCode) Sub else NametagInactive,
                    borderColor = if (data.emblemCode == responseEmblemData.value?.emblemCode) Sub else NametagStroke,
                    emblem = data,
                    onItemClick = { clickedData: Emblem ->
                        responseEmblemData.value =
                            if (responseEmblemData.value?.emblemCode == clickedData.emblemCode) null else clickedData
                        onSelectionChange(responseEmblemData.value)
                    }
                )
            }
        }
    }
}

@Composable
fun ChangeCharacterTitle(
    isSelected: Boolean,
    onClickChange: () -> Unit
) {
    DialogChangeButton(
        text = stringResource(id = R.string.home_change_character_txt),
        textColor = if (isSelected) White else Gray400,
        style = OffroadTheme.typography.textRegular,
        backgroundColor = if (isSelected) Main2 else Black15,
        borderColor = if (isSelected) Main2 else Black25,
        onItemClick = {
            onClickChange()
        }
    )
}
