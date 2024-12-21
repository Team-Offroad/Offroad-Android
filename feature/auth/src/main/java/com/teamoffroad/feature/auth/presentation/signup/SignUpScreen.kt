package com.teamoffroad.feature.auth.presentation.signup

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.addFocusCleaner
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.model.DateValidateResult
import com.teamoffroad.feature.auth.presentation.model.NicknameValidateResult
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
internal fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToSetCharacter: (String, String?, String?) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpUiState by viewModel.signUpUiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val focusManager = LocalFocusManager.current

    BackHandler(
        enabled = pagerState.currentPage == 1 || pagerState.currentPage == 2
    ) {
        coroutineScope.launch {
            when (pagerState.currentPage) {
                1, 2 -> pagerState.animateScrollToPage(
                    pagerState.currentPage - 1,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.signUpSideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.Empty -> TODO()
                SignUpSideEffect.Error -> TODO()
                SignUpSideEffect.Loading -> TODO()
                SignUpSideEffect.Success -> navigateToSetCharacter(
                    signUpUiState.nickname,
                    signUpUiState.date,
                    signUpUiState.selectedGender
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .addFocusCleaner(focusManager)
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
                modifier = Modifier
                    .alpha(
                        if (pagerState.currentPage == 1 || pagerState.currentPage == 2) 1f else 0f,
                    )
                    .padding(top = 40.dp)
                    .clickableWithoutRipple {
                        if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                            if (pagerState.currentPage == 1) viewModel.initBirthDate()
                        } else {
                            viewModel.initGender()
                            viewModel.navigateSetCharacter()
                        }
                    },
                text = stringResource(R.string.auth_skip),
                color = Gray300,
                style = OffroadTheme.typography.hint,
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
            modifier = Modifier
                .padding(bottom = 64.dp)
                .fillMaxWidth(),
            text = when (pagerState.currentPage) {
                0 -> stringResource(R.string.auth_set_nickname_sub_title)
                1 -> stringResource(R.string.auth_set_birth_date_sub_title)
                2 -> stringResource(R.string.auth_set_gender_sub_title)
                else -> stringResource(R.string.auth_set_nickname_sub_title)
            },
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
                0 -> NicknameScreen(
                    focusManager = focusManager,
                    uiState = signUpUiState,
                    updateNicknamesValid = viewModel::updateNicknamesValid,
                    getDuplicateNickname = viewModel::getDuplicateNickname
                )

                1 -> BirthDateScreen(
                    focusManager = focusManager,
                    uiState = signUpUiState,
                    updateYear = viewModel::updateYear,
                    updateMonth = viewModel::updateMonth,
                    updateDate = viewModel::updateDay,
                    updateMonthLength = viewModel::updateMonthLength,
                    updateDateLength = viewModel::updateDayLength
                )

                2 -> GenderScreen(
                    uiState = signUpUiState,
                    updateCheckedGender = viewModel::updateCheckedGender
                )

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
                if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.navigateSetCharacter()
                }
            },
            isActive = when (pagerState.currentPage) {
                0 -> signUpUiState.nicknameScreenResult == NicknameValidateResult.Success
                1 -> signUpUiState.yearValidateResult == DateValidateResult.Success &&
                        signUpUiState.monthValidateResult == DateValidateResult.Success &&
                        signUpUiState.dayValidateResult == DateValidateResult.Success

                2 -> signUpUiState.genderScreenResult
                else -> false
            },
        )
    }
}