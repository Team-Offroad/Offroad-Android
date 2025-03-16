package com.teamoffroad.feature.diary.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.diary.component.DiaryHeader
import com.teamoffroad.feature.diary.component.DiaryHintDialog
import com.teamoffroad.feature.diary.component.DiaryTimeBottomSheet
import com.teamoffroad.feature.diary.component.MemoryLightScreen
import com.teamoffroad.feature.diary.component.OrbDiary
import com.teamoffroad.feature.diary.component.OrbDiaryEmpty
import com.teamoffroad.feature.diary.component.TimeSettingDialog
import com.teamoffroad.feature.diary.presentation.model.DiaryHintDialogState
import com.teamoffroad.feature.diary.presentation.model.DiaryShownState
import com.teamoffroad.feature.diary.presentation.model.DiarySideEffect
import com.teamoffroad.feature.diary.presentation.util.convertRegexToDate
import com.teamoffroad.offroad.feature.diary.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DiaryScreen(
    newDiaryExist: Boolean,
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (String) -> Unit,
    navigateToDiaryTime: () -> Unit,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    val diaryUiState by viewModel.diaryUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.diarySideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                DiarySideEffect.Empty -> {
                }

                DiarySideEffect.NavigateBack -> navigateToBack()
                DiarySideEffect.NavigateDiaryTime -> navigateToDiaryTime()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getDiaryFirstDate()
            getDiaryTutorialChecked()
            getDiaryCreateTimeChecked()
            if (newDiaryExist) updateLatestMemoryLight()
        }
    }

    LaunchedEffect(diaryUiState.currentDiaryCalendarPage) {
        val (year, month) = convertRegexToDate(diaryUiState.currentDiaryCalendarPage)
        viewModel.getDummyHexCode(year = year, month = month)
    }

    LaunchedEffect(diaryUiState.memoryLightList) {
        if (diaryUiState.memoryLightList.memoryLight.isNotEmpty()) {
            viewModel.updateMemoryLightState(true)
        }
    }

    BackHandler {
        viewModel.updateNavigationBackState()
    }

    Box(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Main1)
                .actionBarPadding(),
        ) {
            NavigateBackAppBar(
                text = stringResource(id = R.string.diary_back_home),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                viewModel.updateNavigationBackState()
            }
            DiaryHeader(
                text = stringResource(id = R.string.diary_memory_light)
            ) {
                viewModel.updateHintDialogState(true)
            }
            HorizontalDivider(
                color = Gray100,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .background(ListBg)
                    .fillMaxSize()
            ) {
                when (diaryUiState.diaryShown) {
                    DiaryShownState.DiaryShown -> {
                        OrbDiary(
                            diaryUiState = diaryUiState,
                            diaryFirstCreatedDate = diaryUiState.diaryFirstCreatedDate,
                            dateButtonClick = viewModel::updateMemoryLightInfo,
                            diaryTitleClick = viewModel::updateBottomSheetState,
                            diaryMoveClick = viewModel::updateCurrentDiaryPage,
                            modifier = Modifier.padding(top = 20.dp),
                        )
                    }

                    DiaryShownState.DiaryEmpty -> {
                        OrbDiaryEmpty(
                            navigateToCharacterChat = navigateToCharacterChat,
                            modifier = Modifier.padding(top = 124.dp),
                        )
                    }
                }
            }
        }
        if (diaryUiState.dialogVisibility == DiaryHintDialogState.HintDialogVisible) {
            DiaryHintDialog(
                onCancelClick = viewModel::updateHintDialogState,
                updateTimeSettingDialogState = viewModel::updateTimeSettingDialogState,
                patchDiaryTutorialChecked = viewModel::patchDiaryTutorialChecked,
            )
        }

        if (diaryUiState.timeSettingDialogVisibility) {
            TimeSettingDialog(
                onDefaultTimeSettingClick = {},
                onSettingClick = viewModel::updateTimeSettingDialogState,
                navigateDiaryTime = viewModel::updateNavigationDiaryTime,
                patchDiaryCreateTimeChecked = viewModel::patchDiaryCreateTimeChecked,
            )
        }

        if (diaryUiState.memoryLigthVisibility) {
            MemoryLightScreen(
                memoryLight = diaryUiState.memoryLightList,
                onCancelClick = viewModel::updateMemoryLightState,
                modifier = Modifier.fillMaxSize(),
            )
        }

        if (diaryUiState.bottomSheetVisibility) {
            DiaryTimeBottomSheet(
                currentDiaryCalendarPage = diaryUiState.currentDiaryCalendarPage,
                diaryFirstCreatedDate = diaryUiState.diaryFirstCreatedDate,
                diaryTitleClick = viewModel::updateBottomSheetState,
                diaryMoveClick = viewModel::updateCurrentDiaryPage,
            )
        }
    }
}