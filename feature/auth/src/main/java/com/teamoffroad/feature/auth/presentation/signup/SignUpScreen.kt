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
import androidx.compose.ui.focus.FocusDirection
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
import com.teamoffroad.feature.auth.presentation.model.SignUpPage
import com.teamoffroad.feature.auth.presentation.model.SignUpPage.Companion.toSignUpPage
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
internal fun SignUpScreen(
    navigateToSetCharacter: (String, String?, String?) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val signUpUiState by viewModel.signUpUiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val focusManager = LocalFocusManager.current
    val birthDateFocusManager = LocalFocusManager.current

    BackHandler(
        enabled = pagerState.currentPage.toSignUpPage() == SignUpPage.BIRTHDATE || pagerState.currentPage.toSignUpPage() == SignUpPage.GENDER
    ) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(
                pagerState.currentPage - 1,
            )
        }
        when (pagerState.currentPage.toSignUpPage()) {
            SignUpPage.NICKNAME -> {}
            SignUpPage.BIRTHDATE -> viewModel.initBirthDate()
            SignUpPage.GENDER -> viewModel.initGender()
            SignUpPage.ELSE -> {}
        }
    }

    LaunchedEffect(Unit) {
        viewModel.signUpSideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.NavigateSetCharacter -> navigateToSetCharacter(
                    signUpUiState.nickname,
                    signUpUiState.date,
                    signUpUiState.selectedGender
                )

                SignUpSideEffect.FocusNext -> birthDateFocusManager.moveFocus(
                    FocusDirection.Next
                )

                SignUpSideEffect.FocusClear -> birthDateFocusManager.clearFocus()
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
                        if (pagerState.currentPage.toSignUpPage() == SignUpPage.BIRTHDATE || pagerState.currentPage.toSignUpPage() == SignUpPage.GENDER) 1f else 0f,
                    )
                    .padding(top = 40.dp)
                    .clickableWithoutRipple {
                        if (pagerState.currentPage.toSignUpPage() == SignUpPage.NICKNAME || pagerState.currentPage.toSignUpPage() == SignUpPage.BIRTHDATE) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                            if (pagerState.currentPage.toSignUpPage() == SignUpPage.BIRTHDATE) viewModel.initBirthDate()
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
                .padding(bottom = 56.dp)
                .fillMaxWidth(),
            text = when (pagerState.currentPage.toSignUpPage()) {
                SignUpPage.NICKNAME -> stringResource(R.string.auth_set_nickname_sub_title)
                SignUpPage.BIRTHDATE -> stringResource(R.string.auth_set_birth_date_sub_title)
                SignUpPage.GENDER -> stringResource(R.string.auth_set_gender_sub_title)
                else -> stringResource(R.string.auth_set_nickname_sub_title)
            },
            color = Main2,
            style = OffroadTheme.typography.subtitleReg,
            textAlign = TextAlign.Center
        )
        HorizontalPager(
            modifier = Modifier.weight(2f),
            state = pagerState,
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
        ) { page ->
            when (page.toSignUpPage()) {
                SignUpPage.NICKNAME -> NicknameScreen(
                    focusManager = focusManager,
                    uiState = signUpUiState,
                    updateNicknamesValid = viewModel::updateNicknamesValid,
                    getDuplicateNickname = viewModel::getDuplicateNickname
                )

                SignUpPage.BIRTHDATE -> BirthDateScreen(
                    focusManager = birthDateFocusManager,
                    uiState = signUpUiState,
                    updateYear = viewModel::updateYear,
                    updateMonth = viewModel::updateMonth,
                    updateDate = viewModel::updateDay,
                    updateMonthLength = viewModel::updateMonthLength,
                    updateDateLength = viewModel::updateDayLength,
                    updateFocus = viewModel::updateBirthDateFocus,
                )

                SignUpPage.GENDER -> GenderScreen(
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
            text = stringResource(R.string.auth_next),
            onClick = {
                if (pagerState.currentPage.toSignUpPage() == SignUpPage.NICKNAME || pagerState.currentPage.toSignUpPage() == SignUpPage.BIRTHDATE) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.navigateSetCharacter()
                }
            },
            isActive = when (pagerState.currentPage.toSignUpPage()) {
                SignUpPage.NICKNAME -> signUpUiState.nicknameScreenResult == NicknameValidateResult.Success
                SignUpPage.BIRTHDATE -> signUpUiState.yearValidateResult == DateValidateResult.Success &&
                        signUpUiState.monthValidateResult == DateValidateResult.Success &&
                        signUpUiState.dayValidateResult == DateValidateResult.Success

                SignUpPage.GENDER -> signUpUiState.genderScreenResult
                else -> false
            },
        )
    }
}