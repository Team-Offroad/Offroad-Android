package com.teamoffroad.feature.mypage.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsDialog
import com.teamoffroad.feature.mypage.presentation.component.LogoutDialog
import com.teamoffroad.feature.mypage.presentation.component.SettingContainer
import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.feature.mypage.presentation.component.WithDrawDialog
import com.teamoffroad.offroad.feature.mypage.R
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    navigateToAnnouncement: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val isSettingUiState by viewModel.settingUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var snackBarShowState by remember { mutableStateOf(false) }

    LaunchedEffect(snackBarShowState) {
        if (snackBarShowState) {
            coroutineScope.launch {
                val snackBar = snackBarHostState.showSnackbar(
                    message = if (isSettingUiState.marketingAgree == true) context.getString(R.string.my_page_setting_marketing_snackbar_agree) else context.getString(
                        R.string.my_page_setting_marketing_snackbar_disagree
                    ),
                    actionLabel = "닫기",
                    duration = SnackbarDuration.Short
                )
                when (snackBar) {
                    SnackbarResult.ActionPerformed -> {}
                    SnackbarResult.Dismissed -> {}
                }
            }
            snackBarShowState = false
        }
    }

    LaunchedEffect(isSettingUiState) {
        if (isSettingUiState.reset) navigateToSignIn()
    }

    Column(
        modifier = modifier
            .navigationPadding()
            .background(Main1)
            .fillMaxSize()
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_my_page),
            modifier = modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        SettingHeader(
            text = stringResource(R.string.my_page_setting_title),
            painterResources = R.drawable.ic_setting_tag
        )
        HorizontalDivider(
            color = Gray100,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_announcement),
            isImportant = false,
            onClick = navigateToAnnouncement
        )
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_play_guide),
            isImportant = false,
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://tan-antlion-a47.notion.site/105120a9d80f80cea574f7d62179bfa8")
                )
                context.startActivity(intent)
            })
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_service_term),
            isImportant = false,
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://tan-antlion-a47.notion.site/90c70d8bf0974b37a3a4470022df303d")
                )
                context.startActivity(intent)
            })
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_personal_information),
            isImportant = false,
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://tan-antlion-a47.notion.site/105120a9d80f80739f54fa78902015d7")
                )
                context.startActivity(intent)
            })
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_marketing_agree),
            isImportant = false,
            onClick = { viewModel.changeDialogState(SettingDialogState.MarketingVisible) })
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_logout),
            isImportant = false,
            onClick = { viewModel.changeDialogState(SettingDialogState.LogoutVisible) })
        SettingContainer(
            title = stringResource(R.string.my_page_setting_item_withdraw),
            isImportant = false,
            onClick = { viewModel.changeDialogState(SettingDialogState.WithDrawVisible) })
        Spacer(modifier = Modifier.weight(1f))
        SnackbarHost(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            hostState = snackBarHostState
        )
    }

    when (isSettingUiState.dialogVisible) {
        SettingDialogState.InVisible -> {}
        SettingDialogState.MarketingVisible -> AgreeTermsAndConditionsDialog(
            title = stringResource(R.string.my_page_setting_marketing_dialog_title),
            content = stringResource(com.teamoffroad.offroad.feature.auth.R.string.auth_agree_and_terms_conditions_dialog_marketing_content),
            onAgreeClick = { viewModel.changedMarketingAgree(true) },
            onDisAgreeClick = { viewModel.changedMarketingAgree(false) },
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.InVisible)
                snackBarShowState = true
            })

        SettingDialogState.LogoutVisible -> LogoutDialog(
            onClick = { viewModel.performSignOut() },
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.InVisible)
            },
        )

        SettingDialogState.WithDrawVisible -> WithDrawDialog(
            isWithDrawText = isSettingUiState.withDrawInputState,
            isWithDrawResult = isSettingUiState.withDrawResult,
            onInputTextChange = viewModel::changeWithDrawInputText,
            onClick = viewModel::deleteUserInfo,
            withDrawInputText = viewModel.settingUiState.value.withDrawInputState,
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.InVisible)
            })
    }
}

