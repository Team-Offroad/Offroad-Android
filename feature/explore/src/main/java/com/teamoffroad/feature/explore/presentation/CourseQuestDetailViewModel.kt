package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseQuestDetailViewModel
    @Inject
    constructor() : ViewModel() {
        fun loadQuestDetails(questId: Long) {
            // TODO
        }
    }
