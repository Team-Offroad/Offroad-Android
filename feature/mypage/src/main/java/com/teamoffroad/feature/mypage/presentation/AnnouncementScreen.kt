package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.SettingContainer
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun AnnouncementScreen(
    navigateToAnnouncement: () -> Unit,
    navigateToBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = "설정",
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        SettingHeader(text = "공지사항", painterResources = R.drawable.ic_announcement_tag)
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .background(color = Gray100)
                .padding(bottom = 24.dp)
        )
        SettingContainer(color = Main1, text = "의견을 들려주세요", click = navigateToAnnouncement)
        SettingContainer(color = Main1, text = "락이좋아요", click = {})
        SettingContainer(color = Main1, text = "제휴 업체 안내", click = {})
        SettingContainer(color = Main1, text = "운영 관련 안내", click = {})
        SettingContainer(color = Main1, text = "이벤트 관련 안내", click = {})
        SettingContainer(color = Main1, text = "오프로드 고객센터 추석 휴무 안내", click = {})
        SettingContainer(color = Main1, text = "운영 관련 사항을 알려드립니다.", click = {})
    }
}