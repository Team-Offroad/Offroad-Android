package com.teamoffroad.feature.mypage.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.GainedEmblemsHeader
import com.teamoffroad.feature.mypage.presentation.component.GainedEmblemsItems
import com.teamoffroad.offroad.feature.mypage.R

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun GainedEmblemsScreen(
    navigateToBack: () -> Unit,
    viewModel: GainedEmblemsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getEmblems()
    }
    Column(
        modifier = Modifier
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_my_page),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        GainedEmblemsHeader()
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(color = Gray100))
        viewModel.gainedEmblems.value?.let { GainedEmblemsItems(gainedEmblemsList = it) }
    }
}