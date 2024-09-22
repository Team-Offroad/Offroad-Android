package com.teamoffroad.feature.mypage.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.LogoutDialog
import com.teamoffroad.feature.mypage.presentation.component.MarketingInfoDialog
import com.teamoffroad.feature.mypage.presentation.component.SettingContainer
import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.feature.mypage.presentation.component.WithDrawDialog
import com.teamoffroad.offroad.feature.mypage.R

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    navigateToAnnouncement: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val isSettingUiState by viewModel.settingUiState.collectAsState()

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
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Gray100)
        )
        Spacer(Modifier.height(24.dp))
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_announcement),
            isImportant = false,
            onClick = navigateToAnnouncement
        )
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_play_guide),
            isImportant = false,
            onClick = {})
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_service_term),
            isImportant = false,
            onClick = {})
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_personal_information),
            isImportant = false,
            onClick = {})
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_marketing_agree),
            isImportant = false,
            onClick = { viewModel.changeDialogState(SettingDialogState.MarketingVisible) })
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_logout),
            isImportant = false,
            onClick = { viewModel.changeDialogState(SettingDialogState.LogoutVisible) })
        SettingContainer(
            color = Main1,
            text = stringResource(R.string.my_page_setting_item_withdraw),
            isImportant = false,
            onClick = { viewModel.changeDialogState(SettingDialogState.WithDrawVisible) })
    }

    when (isSettingUiState.dialogVisible) {
        SettingDialogState.InVisible -> {}
        SettingDialogState.MarketingVisible -> MarketingInfoDialog(
            onClick = viewModel::changedMarketingAgree,
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.InVisible)
            })

        SettingDialogState.LogoutVisible -> LogoutDialog(
            onClick = { viewModel.performSignOut() },
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.InVisible)
            },
            navigateToSignIn = navigateToSignIn
        )

        SettingDialogState.WithDrawVisible -> WithDrawDialog(
            isWithDrawText = isSettingUiState.withDrawInputState,
            isWithDrawResult = isSettingUiState.withDrawResult,
            onInputTextChange = viewModel::changeWithDrawInputText,
            isWithDrawResultChanged = { viewModel.changeWithDrawInputTextResult() },
            onClick = viewModel::deleteUserInfo,
            withDrawInputText = viewModel.settingUiState.value.withDrawInputState,
            navigateToSignIn = navigateToSignIn,
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.InVisible)
            })
    }
}

