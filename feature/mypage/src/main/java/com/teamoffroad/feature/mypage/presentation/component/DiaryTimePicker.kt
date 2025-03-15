package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.diary.component.NumberPicker
import com.teamoffroad.feature.diary.component.rememberPickerState

@Composable
fun DiaryTimePicker(
    updateDiaryTime: (Boolean, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        val hoursValue = remember {
            (1..12).map {
                if (it < 10) "  $it     00"
                else "$it    00"
            }
        }
        val hoursValuesPickerState = rememberPickerState()

        val meridiemValue = remember { listOf("AM", "PM") }
        val meridiemValuePickerState = rememberPickerState()

        LaunchedEffect(hoursValuesPickerState.selectedItem) {
            updateDiaryTime(true, hoursValuesPickerState.selectedItem.take(3))
        }

        LaunchedEffect(meridiemValuePickerState.selectedItem) {
            updateDiaryTime(false, meridiemValuePickerState.selectedItem)
        }

        Box {
            Box(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(7.dp),
                        color = ListBg
                    )
                    .height(36.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                NumberPicker(
                    pickerState = hoursValuesPickerState,
                    items = hoursValue,
                    visibleItemsCount = 3,
                    textStyle = OffroadTheme.typography.subtitleReg,
                    timeDivider = true,
                    width = 120.dp,
                    textModifier = Modifier.padding(4.dp),
                )
                NumberPicker(
                    pickerState = meridiemValuePickerState,
                    items = meridiemValue,
                    visibleItemsCount = 3,
                    textStyle = OffroadTheme.typography.subtitleReg,
                    isInfinitelyScroll = false,
                    width = 44.dp,
                    textModifier = Modifier.padding(4.dp),
                )
            }
        }
    }
}