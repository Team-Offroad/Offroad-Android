package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.mypage.presentation.component.AnnouncementDetailHeader
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun AnnouncementDetailScreen(
    title: String,
    content: String,
    isImportant: Boolean,
    updateAt: String,
    navigateToBack: () -> Unit,
    link: String = "",
) {
    val (year, month, day) = extractAnnounceUpdateDate(updateAt)

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_setting_announcement_detail_back),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        AnnouncementDetailHeader(year, month, day, title, isImportant)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Gray100)
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = content,
            style = OffroadTheme.typography.textRegular,
            color = Main2,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )
        Text(
            text = link,
            style = OffroadTheme.typography.textRegular,
            color = Sub2,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )
    }
}

private fun extractAnnounceUpdateDate(updateAt: String): Triple<String, String, String> {
    val datePart = updateAt.split("T")[0]
    val (year, month, day) = datePart.split("-")
    return Triple(year, month, day)
}