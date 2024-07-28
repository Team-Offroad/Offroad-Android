package com.teamoffroad.feature.home.presentation.component.dialog

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import com.teamoffroad.feature.home.presentation.model.UserChangeEmblemDialogStateModel
import com.teamoffroad.offroad.feature.home.R

@Composable
fun ChangeEmblemDialog(
    showDialog: MutableState<Boolean>,
    userChangeEmblemDialogStateModel: MutableState<UserChangeEmblemDialogStateModel?>,
    originEmblem: String,
    onClickCancel: () -> Unit,
    onCharacterChange: (Emblem?) -> Unit,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val emblemListState =
        viewModel.getEmblemsState.collectAsState(initial = UiState.Loading).value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getEmblems()
    }

    val emblemsData = when (emblemListState) {
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
                .fillMaxWidth()
                .height(380.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Box {
                CloseDialog(
                    onClickCancel = {
                        showDialog.value = false
                        userChangeEmblemDialogStateModel.value?.onClickCancel
                    }
                )

                val selectedItem = remember { mutableStateOf<Emblem?>(null) }

                Column(
                    modifier = Modifier.padding(start = 22.dp, end = 22.dp, bottom = 12.dp)
                ) {
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    DialogTitle()
                    Spacer(modifier = Modifier.padding(top = 22.dp))
                    if (emblemsData != null) {
                        CharacterTitle(
                            emblems = emblemsData,
                            originEmblem = originEmblem
                        ) { itemSelected ->
                            selectedItem.value = itemSelected
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .weight(1f)
                    )
                    ChangeCharacterTitle(
                        isSelected = selectedItem.value != null,
                        onClickChange = {
                            onCharacterChange(selectedItem.value)
                            showDialog.value = false
                            userChangeEmblemDialogStateModel.value?.onClickCancel
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DialogTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.home_collected_character_name),
        style = OffroadTheme.typography.title,
        textAlign = TextAlign.Center
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun CharacterTitle(
    emblems: List<Emblem>,
    originEmblem: String,
    onSelectionChange: (Emblem?) -> Unit
) {
    val selectedEmblemData = remember { mutableStateOf<Emblem?>(null) }
    val clickedOriginEmblem = remember { mutableStateOf(false) }
    val emblemDataState = rememberLazyListState()

    Box(
        modifier = Modifier.height(222.dp)
    ) {
        LazyColumn(
            state = emblemDataState,
            modifier = Modifier
                .drawScrollbar(emblemDataState),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(emblems, key = { it.emblemName }) { emblem ->
                val selectedChangeEmblemState = when (emblem.emblemName) {
                    originEmblem -> SelectedEmblemState.SAME_WITH_ORIGIN_EMBLEM
                    selectedEmblemData.value?.emblemName -> SelectedEmblemState.SAME_WITH_SELECTED_CHANGE_EMBLEM
                    else -> SelectedEmblemState.SELECTED_CHANGE_EMBLEM
                }

                DialogTagItem(
                    text = emblem.emblemName,
                    textColor =
                    when (selectedChangeEmblemState) {
                        SelectedEmblemState.SAME_WITH_ORIGIN_EMBLEM -> if (clickedOriginEmblem.value) Main2 else White
                        SelectedEmblemState.SAME_WITH_SELECTED_CHANGE_EMBLEM -> White
                        SelectedEmblemState.SELECTED_CHANGE_EMBLEM -> Main2
                    },
                    style = OffroadTheme.typography.subtitle2Semibold,
                    backgroundColor =
                    when (selectedChangeEmblemState) {
                        SelectedEmblemState.SAME_WITH_ORIGIN_EMBLEM -> if (clickedOriginEmblem.value) NametagInactive else Sub
                        SelectedEmblemState.SAME_WITH_SELECTED_CHANGE_EMBLEM -> Sub
                        SelectedEmblemState.SELECTED_CHANGE_EMBLEM -> NametagInactive
                    },
                    borderColor =
                    when (selectedChangeEmblemState) {
                        SelectedEmblemState.SAME_WITH_ORIGIN_EMBLEM -> if (clickedOriginEmblem.value) NametagStroke else Sub
                        SelectedEmblemState.SAME_WITH_SELECTED_CHANGE_EMBLEM -> Sub
                        SelectedEmblemState.SELECTED_CHANGE_EMBLEM -> NametagStroke
                    },
                    emblem = emblem,
                    onItemClick = { clickedData: Emblem ->
                        when (selectedChangeEmblemState) {
                            SelectedEmblemState.SAME_WITH_ORIGIN_EMBLEM -> {
                                selectedEmblemData.value = null
                                clickedOriginEmblem.value = clickedOriginEmblem.value.not()
                            }

                            SelectedEmblemState.SAME_WITH_SELECTED_CHANGE_EMBLEM -> {
                                selectedEmblemData.value = null
                                clickedOriginEmblem.value = true
                            }

                            SelectedEmblemState.SELECTED_CHANGE_EMBLEM -> {
                                selectedEmblemData.value = clickedData
                                clickedOriginEmblem.value = true
                            }

                        }

                        onSelectionChange(selectedEmblemData.value)
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
            if (isSelected) onClickChange()
        }
    )
}
