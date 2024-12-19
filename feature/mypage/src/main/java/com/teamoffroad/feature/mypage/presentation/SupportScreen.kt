package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun SupportScreen(
    navigateToBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Main1)
            .navigationPadding()
            .actionBarPadding(),
    ) {
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_settings),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        SettingHeader(
            text = stringResource(R.string.my_page_setting_customer_support),
            painterResources = R.drawable.ic_customer_support,
        )
        HorizontalDivider(
            color = Gray100,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
        ) {
            SupportDescription()
            SupportDescriptionLabel()
            SupportEmailBox()
        }
    }
}

@Composable
private fun SupportDescription() {
    Text(
        text = stringResource(R.string.my_page_support_description),
        style = OffroadTheme.typography.textBold,
        color = Main2,
        modifier = Modifier.padding(top = 30.dp),
    )
}

@Composable
private fun SupportDescriptionLabel() {
    Text(
        text = stringResource(R.string.my_page_support_description_label),
        style = OffroadTheme.typography.textAuto,
        color = Gray400,
        modifier = Modifier.padding(top = 12.dp),
    )
}

@Composable
private fun SupportEmailBox() {
    Text(
        text = stringResource(R.string.my_page_support_email),
        style = OffroadTheme.typography.textRegular,
        color = Main2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp)
            .background(color = NametagInactive, shape = RoundedCornerShape(5.dp))
            .padding(vertical = 14.dp),
    )
}
