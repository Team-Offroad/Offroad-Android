package com.teamoffroad.feature.diary.presentation.model

interface DiaryHintDialogState {
    data object HintDialogInVisible : DiaryHintDialogState
    data object HintDialogVisible : DiaryHintDialogState
}