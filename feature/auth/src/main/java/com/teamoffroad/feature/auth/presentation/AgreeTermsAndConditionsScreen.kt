package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsItem
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBar
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBarAllAgreeBox
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.model.AgreeTermsAndConditionsUiState
import kotlin.reflect.KFunction1

@Composable
internal fun AgreeTermsAndConditionsScreen(
    navigateToSetNicknameScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AgreeTermsAndConditionsViewModel = hiltViewModel(),
) {
    val isAllChecked by viewModel.allChecked.collectAsState()
    val isServiceUtil by viewModel.serviceUtil.collectAsState()
    val isPersonalInfo by viewModel.personalInfo.collectAsState()
    val isLocation by viewModel.location.collectAsState()
    val isMarketing by viewModel.marketing.collectAsState()
    val isAgreeTermsAndConditionsUiState by viewModel.agreeTermsAndConditionsUiState.collectAsState()
    checkRequired(
        isServiceUtil = isServiceUtil,
        isLocation = isLocation,
        isPersonalInfo = isPersonalInfo,
        updateAgreeTermsAndConditionsUiState = viewModel::updateAgreeTermsAndConditionsUiState
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Main1)
    ) {
        AgreeTermsAndConditionsTopBar(modifier.padding(bottom = 24.dp))
        AgreeTermsAndConditionsTopBarAllAgreeBox(
            isChecked = isAllChecked,
            onClick = { viewModel.allCheckedChangedListener() },
            modifier = Modifier.padding(bottom = 22.dp)
        )
        AgreeTermsAndConditionsItem(
            text = "서비스 이용 약관",
            isChecked = isServiceUtil,
            onClick = { viewModel.serviceCheckedChangedListener() })
        AgreeTermsAndConditionsItem(
            text = "개인정보수집/이용 동의",
            isChecked = isPersonalInfo,
            onClick = { viewModel.personalCheckedChangedListener() })
        AgreeTermsAndConditionsItem(
            text = "위치기반 서비스 이용약관",
            isChecked = isLocation,
            onClick = { viewModel.locationCheckedChangedListener() })
        AgreeTermsAndConditionsItem(
            text = "마케팅 정보 수신 동의",
            isChecked = isMarketing,
            onClick = { viewModel.marketingCheckedChangedListener() })
        Spacer(modifier = Modifier.weight(1f))
        OffroadBasicBtn(
            text = "다음",
            onClick = navigateToSetNicknameScreen,
            isActive = isAgreeTermsAndConditionsUiState.name == AgreeTermsAndConditionsUiState.REQUIRED.name,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp),
        )
    }
}

fun checkRequired(
    isServiceUtil: Boolean,
    isPersonalInfo: Boolean,
    isLocation: Boolean,
    updateAgreeTermsAndConditionsUiState: KFunction1<Boolean, Unit>,
) {
    if (isLocation && isServiceUtil && isPersonalInfo) {
        updateAgreeTermsAndConditionsUiState(true)
    } else
        updateAgreeTermsAndConditionsUiState(false)
}