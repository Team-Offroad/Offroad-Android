package com.teamoffroad.feature.diary.presentation.model

interface DiaryShownState {
    data object DiaryUnShown : DiaryShownState
    data object DiaryShown : DiaryShownState
    data object DiaryEmpty : DiaryShownState
}