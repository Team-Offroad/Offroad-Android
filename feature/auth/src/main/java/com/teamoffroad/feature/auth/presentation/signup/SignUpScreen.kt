package com.teamoffroad.feature.auth.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpUiState by viewModel.signUpUiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    LaunchedEffect(Unit) {
        viewModel.signUpSideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.Empty -> TODO()
                SignUpSideEffect.Error -> TODO()
                SignUpSideEffect.Loading -> TODO()
                SignUpSideEffect.Success -> navigateToHome()
            }
        }
    }

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .background(Main1),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OffroadActionBar()
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.auth_skip),
                color = Gray300,
                style = OffroadTheme.typography.hint,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clickableWithoutRipple {

                    }
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 6.dp),
            text = stringResource(R.string.auth_on_boarding_title),
            color = Main2,
            style = OffroadTheme.typography.profileTitle,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.auth_set_gender_sub_title),
            color = Main2,
            style = OffroadTheme.typography.subtitleReg,
            textAlign = TextAlign.Center
        )
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
        ) { page ->
            when (page) {
                0 -> {}
                1 -> {}
                2 -> {}
                else -> {}
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        OffroadBasicBtn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp),
            text = stringResource(R.string.auth_basic_button),
            onClick = {

            },
            isActive = true,
        )
    }
}