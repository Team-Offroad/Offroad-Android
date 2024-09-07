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
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsDialog
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsItem
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBar
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBarAllAgreeBox
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.model.AgreeTermsAndConditionsUiState
import com.teamoffroad.feature.auth.presentation.model.DialogState
import kotlin.reflect.KFunction1

@Composable
internal fun AgreeTermsAndConditionsScreen(
    navigateToSetNicknameScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AgreeTermsAndConditionsViewModel = hiltViewModel(),
) {
    val isDialogShown by viewModel.dialogState.collectAsState()
    val isAllChecked by viewModel.allChecked.collectAsState()
    val isServiceUtil by viewModel.serviceUtil.collectAsState()
    val isPersonalInfo by viewModel.personalInfo.collectAsState()
    val isLocation by viewModel.location.collectAsState()
    val isMarketing by viewModel.marketing.collectAsState()
    val isAgreeTermsAndConditionsUiState by
    viewModel.agreeTermsAndConditionsUiState.collectAsState()
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
            onButtonClick = {},
            modifier = Modifier.padding(bottom = 22.dp)
        )
        AgreeTermsAndConditionsItem(
            text = "서비스 이용 약관",
            isChecked = isServiceUtil,
            onClick = { viewModel.serviceCheckedChangedListener() },
            dialogShown = { viewModel.changeDialogState(DialogState.SERVICE_DIALOG) }
        )
        AgreeTermsAndConditionsItem(
            text = "개인정보수집/이용 동의",
            isChecked = isPersonalInfo,
            onClick = { viewModel.personalCheckedChangedListener() },
            dialogShown = { viewModel.changeDialogState(DialogState.PERSONAL_DIALOG) }
        )
        AgreeTermsAndConditionsItem(
            text = "위치기반 서비스 이용약관",
            isChecked = isLocation,
            onClick = { viewModel.locationCheckedChangedListener() },
            dialogShown = { viewModel.changeDialogState(DialogState.LOCATION_DIALOG) }
        )
        AgreeTermsAndConditionsItem(
            text = "마케팅 정보 수신 동의",
            isChecked = isMarketing,
            onClick = { viewModel.marketingCheckedChangedListener() },
            dialogShown = { viewModel.changeDialogState(DialogState.MARKETING_DIALOG) }
        )
        Spacer(modifier = Modifier.weight(1f))
        OffroadBasicBtn(
            text = "다음",
            marketingAgree = { viewModel.changedMarketingAgree(viewModel.marketing.value) },
            onClick = { navigateToSetNicknameScreen() },
            isActive = isAgreeTermsAndConditionsUiState.name == AgreeTermsAndConditionsUiState.REQUIRED.name,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp),
        )
    }

    when (isDialogShown.name) {
        DialogState.SERVICE_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                text = "서비스 이용 약관",
                onClick = { viewModel.serviceDialogCheckedChangedListener() },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }

        DialogState.PERSONAL_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                text = "개인정보수집/이용 동의",
                onClick = { viewModel.personalDialogCheckedChangedListener() },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }

        DialogState.LOCATION_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                text = "위치기반 서비스 이용약관",
                onClick = { viewModel.locationDialogCheckedChangedListener() },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }

        DialogState.MARKETING_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                text = "마케팅 정보 수신 동의",
                onClick = { viewModel.marketingDialogCheckedChangedListener() },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }
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

