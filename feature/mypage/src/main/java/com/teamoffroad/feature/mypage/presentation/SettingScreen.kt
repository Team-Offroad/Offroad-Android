package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.LogoutDialog
import com.teamoffroad.feature.mypage.presentation.component.SettingContainer
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun SettingScreen(
    navigateToAnnouncement: () -> Unit,
    navigateToBack: () -> Unit,
) {
    val isLogOutDialogShown = remember { mutableStateOf(false) }

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
        SettingHeader(text = "설정", painterResources = R.drawable.ic_my_page_emblems_tag)
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .background(color = Gray100)
                .padding(bottom = 24.dp)
        )
        SettingContainer(color = ListBg, text = "공지사항", click = navigateToAnnouncement)
        SettingContainer(color = Main1, text = "플레이 가이드", click = {})
        SettingContainer(color = Main1, text = "서비스 이용약관", click = {})
        SettingContainer(color = Main1, text = "개인정보처리방침", click = {})
        SettingContainer(color = Main1, text = "마케팅 수신동의", click = {})
        SettingContainer(color = Main1, text = "로그아웃", click = { isLogOutDialogShown.value = true })
        SettingContainer(color = Main1, text = "회원 탈퇴", click = {})
    }

    if (isLogOutDialogShown.value) {
        LogoutDialog(showDialog = isLogOutDialogShown,
            onClickCancel = {
                isLogOutDialogShown.value = false
            })

    }
}