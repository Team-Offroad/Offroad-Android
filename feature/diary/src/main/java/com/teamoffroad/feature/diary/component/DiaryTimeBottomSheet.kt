package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.feature.mypage.presentation.component.rememberPickerState
import com.teamoffroad.offroad.feature.diary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryTimeBottomSheet(
    modifier: Modifier = Modifier,
    diaryTitleClick: (Boolean) -> Unit,
    diaryMoveClick: (String?) -> Unit,
) {
    val diaryCalendarPickerState = rememberPickerState()

    ModalBottomSheet(
        onDismissRequest = { diaryTitleClick(false) }) {

        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 30.dp, bottom = 12.dp)
                    .clickableWithoutRipple {
                        diaryMoveClick(diaryCalendarPickerState.selectedItem)
                        diaryTitleClick(false)
                    },
                text = stringResource(id = R.string.diary_time_picker_success),
                color = Sub,
                style = OffroadTheme.typography.tooltipTitle,
            )
            DiaryCalendarPicker(
                diaryCalendarPickerState = diaryCalendarPickerState
            )
        }
    }
}