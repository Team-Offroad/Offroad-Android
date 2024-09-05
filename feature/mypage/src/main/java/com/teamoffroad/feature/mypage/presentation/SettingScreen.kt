package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.LogoutDialog
import com.teamoffroad.feature.mypage.presentation.component.MarketingInfoDialog
import com.teamoffroad.feature.mypage.presentation.component.SettingContainer
import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.feature.mypage.presentation.component.WithDrawDialog
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    navigateToAnnouncement: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val isSettingUiState by viewModel.settingUiState.collectAsState()

    viewModel.patchMarketingInfo()
    Column(
        modifier = modifier
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_my_page),
            modifier = modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        SettingHeader(text = "설정", painterResources = R.drawable.ic_setting_tag)
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Gray100)
        )
        Spacer(Modifier.height(24.dp))
        SettingContainer(
            color = ListBg,
            text = "공지사항",
            isImportant = false,
            click = navigateToAnnouncement
        )
        SettingContainer(color = Main1, text = "플레이 가이드", isImportant = false, click = {})
        SettingContainer(color = Main1, text = "서비스 이용약관", isImportant = false, click = {})
        SettingContainer(color = Main1, text = "개인정보처리방침", isImportant = false, click = {})
        SettingContainer(
            color = Main1,
            text = "마케팅 수신동의",
            isImportant = false,
            click = { viewModel.changeDialogState(SettingDialogState.marketingVisible) })
        SettingContainer(
            color = Main1,
            text = "로그아웃",
            isImportant = false,
            click = { viewModel.changeDialogState(SettingDialogState.logoutVisible) })
        SettingContainer(
            color = Main1,
            text = "회원 탈퇴",
            isImportant = false,
            click = { viewModel.changeDialogState(SettingDialogState.withDrawVisible) })
    }

    when (isSettingUiState.dialogVisible) {
        SettingDialogState.inVisible -> {}
        SettingDialogState.marketingVisible -> MarketingInfoDialog(
            onClick = { viewModel.patchMarketingInfo() },
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.inVisible)
            })

        SettingDialogState.logoutVisible -> LogoutDialog(
            onClick = { viewModel.performSignOut() },
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.inVisible)
            },
            navigateToSignIn = navigateToSignIn
        )

        SettingDialogState.withDrawVisible -> WithDrawDialog(
            isWithDrawText = isSettingUiState.withDrawInputState,
            isWithDrawResult = isSettingUiState.withDrawResult,
            onInputTextChange = viewModel::changeWithDrawInputText,
            isWithDrawResultChanged = { viewModel.changeWithDrawInputTextResult() },
            onClickCancel = {
                viewModel.changeDialogState(SettingDialogState.inVisible)
            })
    }
}

