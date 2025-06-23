package com.teamoffroad.feature.explore.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CourseQuestDetailScreen(
    questId: Long,
    navigateToBack: () -> Unit,
    courseQuestDetailViewModel: CourseQuestDetailViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        courseQuestDetailViewModel.loadQuestDetails(questId)
    }
}
