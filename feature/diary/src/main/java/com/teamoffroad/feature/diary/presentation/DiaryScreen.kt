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
import com.teamoffroad.feature.diary.component.OrbDiary
import com.teamoffroad.feature.diary.component.OrbDiaryEmpty
import com.teamoffroad.offroad.feature.diary.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DiaryScreen(
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (String) -> Unit,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    val isDiaryUiState by viewModel.diaryUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.diarySideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                DiarySideEffect.Empty -> {
                }

                DiarySideEffect.NavigateBack -> navigateToBack()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getLatestDiary()
        }
    }

    BackHandler {
        viewModel.backButtonClickListener()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Main1)
                .navigationPadding()
                .actionBarPadding(),
        ) {
            NavigateBackAppBar(
                text = stringResource(id = R.string.diary_back_home),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                viewModel.backButtonClickListener()
            }
            DiaryHeader(
                text = stringResource(id = R.string.diary_memory_ligth),
                {}
                //TODO.가이드버튼
            )
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
                if (isDiaryUiState.latestDiary.isEmpty())
                    OrbDiaryEmpty(
                        modifier = Modifier.padding(top = 124.dp),
                        navigateToCharacterChat = navigateToCharacterChat
                    )
                else
                    OrbDiary(
                        modifier = Modifier.padding(top = 20.dp)
                    )
            }
        }
    }
}