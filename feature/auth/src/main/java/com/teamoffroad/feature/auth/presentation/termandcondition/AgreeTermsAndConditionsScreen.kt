package com.teamoffroad.feature.auth.presentation.termandcondition

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsDialog
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsItem
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBar
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBarAllAgreeBox
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn
import com.teamoffroad.feature.auth.presentation.model.DialogState
import com.teamoffroad.offroad.feature.auth.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
internal fun AgreeTermsAndConditionsScreen(
    navigateToSetNicknameScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AgreeTermsAndConditionsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val isDialogShown by viewModel.dialogState.collectAsState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isToastShown by remember { mutableStateOf(false) }
    val currentDateTime = remember { LocalDateTime.now() }
    val formatter = remember {
        DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm", Locale.getDefault())
    }

    LaunchedEffect(isToastShown) {
        if (isToastShown) {
            val toastMessage = when (uiState.isMarketing) {
                true -> context.getString(
                    R.string.onboarding_agree_marketing_agree,
                    currentDateTime.format(formatter)
                )

                false -> context.getString(
                    R.string.onboarding_agree_marketing_disagree,
                    currentDateTime.format(formatter)
                )
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            isToastShown = false
        }
    }
    LaunchedEffect(uiState) {
        viewModel.apply {
            updateAgreeTermsAndConditionsUiState()
        }
    }

    Box(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .background(Main1)
    ) {
        Column {
            AgreeTermsAndConditionsTopBar(modifier.padding(bottom = 24.dp))
            AgreeTermsAndConditionsTopBarAllAgreeBox(
                isChecked = uiState.isServiceUtil && uiState.isPersonalInfo && uiState.isLocation && uiState.isMarketing,
                onClick = { viewModel.allCheckedChangedListener() },
                modifier = Modifier.padding(bottom = 22.dp)
            )
            AgreeTermsAndConditionsItem(
                text = stringResource(R.string.auth_agree_and_terms_conditions_service),
                isChecked = uiState.isServiceUtil,
                isRequired = true,
                onClick = { viewModel.serviceCheckedChangedListener() },
                dialogShown = { viewModel.changeDialogState(DialogState.SERVICE_DIALOG) }
            )
            AgreeTermsAndConditionsItem(
                text = stringResource(R.string.auth_agree_and_terms_conditions_personal),
                isChecked = uiState.isPersonalInfo,
                isRequired = true,
                onClick = { viewModel.personalCheckedChangedListener() },
                dialogShown = { viewModel.changeDialogState(DialogState.PERSONAL_DIALOG) }
            )
            AgreeTermsAndConditionsItem(
                text = stringResource(R.string.auth_agree_and_terms_conditions_location),
                isChecked = uiState.isLocation,
                isRequired = true,
                onClick = { viewModel.locationCheckedChangedListener() },
                dialogShown = { viewModel.changeDialogState(DialogState.LOCATION_DIALOG) }
            )
            AgreeTermsAndConditionsItem(
                text = stringResource(R.string.auth_agree_and_terms_conditions_marketing),
                isChecked = uiState.isMarketing,
                isRequired = false,
                onClick = { viewModel.marketingCheckedChangedListener() },
                dialogShown = { viewModel.changeDialogState(DialogState.MARKETING_DIALOG) }
            )
            Spacer(modifier = Modifier.weight(1f))
            OffroadBasicBtn(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 72.dp)
                    .height(50.dp),
                text = stringResource(R.string.auth_basic_button),
                updateState = { viewModel.changedMarketingAgree(uiState.isMarketing) },
                onClick = {
                    isToastShown = true
                    navigateToSetNicknameScreen()
                },
                isActive = uiState.success,
            )
        }
    }

    when (isDialogShown.name) {
        DialogState.SERVICE_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                title = stringResource(R.string.auth_agree_and_terms_conditions_service),
                content = stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_first) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_second) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_third) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_fourth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_fifth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_sixth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_seventh) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_eighth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_ninth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_tenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_eleventh) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_twelfth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_thirteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_fourteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_fifteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_sixteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_seventeenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_eighteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_nineteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_service_content_twentieth),
                onAgreeClick = { viewModel.serviceDialogCheckedChangedListener(true) },
                onDisAgreeClick = { viewModel.serviceDialogCheckedChangedListener(false) },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }

        DialogState.PERSONAL_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                title = stringResource(R.string.auth_agree_and_terms_conditions_personal),
                content = stringResource(R.string.auth_agree_and_terms_conditions_dialog_personal_content_first) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_personal_content_second) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_personal_content_third) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_personal_content_fourth),
                onAgreeClick = { viewModel.personalDialogCheckedChangedListener(true) },
                onDisAgreeClick = { viewModel.personalDialogCheckedChangedListener(false) },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }

        DialogState.LOCATION_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                title = stringResource(R.string.auth_agree_and_terms_conditions_location),
                content = stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_first) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_second) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_third) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_fourth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_fifth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_sixth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_seventh) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_eighth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_ninth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_tenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_eleventh) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_twelfth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_thirteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_fourteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_fifteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_sixteenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_seventeenth) +
                        stringResource(R.string.auth_agree_and_terms_conditions_dialog_location_content_eighteenth),
                onAgreeClick = { viewModel.locationDialogCheckedChangedListener(true) },
                onDisAgreeClick = { viewModel.locationDialogCheckedChangedListener(false) },
                onClickCancel = { viewModel.changeDialogState(DialogState.EMPTY) })
        }

        DialogState.MARKETING_DIALOG.name -> {
            AgreeTermsAndConditionsDialog(
                title = stringResource(R.string.auth_agree_and_terms_conditions_marketing),
                content = stringResource(R.string.auth_agree_and_terms_conditions_dialog_marketing_content),
                onAgreeClick = { viewModel.marketingDialogCheckedChangedListener(true) },
                onDisAgreeClick = { viewModel.marketingDialogCheckedChangedListener(false) },
                onClickCancel = {
                    viewModel.changeDialogState(DialogState.EMPTY)
                    isToastShown = true
                })
        }
    }
}
