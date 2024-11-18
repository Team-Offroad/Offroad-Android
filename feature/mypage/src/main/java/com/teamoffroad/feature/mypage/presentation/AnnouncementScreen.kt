package com.teamoffroad.feature.mypage.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.AnnouncementItems
import com.teamoffroad.feature.mypage.presentation.component.SettingContainer
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.feature.mypage.presentation.model.AnnouncementResult
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun AnnouncementScreen(
    announcementId: String?,
    navigateToAnnouncementDetail: (String, String, Boolean, String, Boolean, List<String>, List<String>) -> Unit,
    navigateToBack: () -> Unit,
    viewModel: AnnouncementViewModel = hiltViewModel()
) {
    val isAnnouncementState by viewModel.announcementUiState.collectAsState()
    //TODO. id 어떻게 초기화?
    Log.d("asdsad", announcementId.toString())

    LaunchedEffect(Unit) {
        viewModel.updateAnnouncement()
    }

    LaunchedEffect(Unit) {
        if (announcementId != null) {
            isAnnouncementState.announcementList.forEach {
                if (it.title.trim().equals(announcementId.trim(), ignoreCase = true)) {
                    Log.d("asdsad", "Navigating to detail for $announcementId")
                    navigateToAnnouncementDetail(
                        it.title,
                        it.content,
                        it.isImportant,
                        it.updateAt,
                        it.hasExternalLinks,
                        it.externalLinks,
                        it.externalLinksTitles,
                    )
                }
            }
        }
    }
    LaunchedEffect(isAnnouncementState) {
        if (announcementId != null) {
            isAnnouncementState.announcementList.forEach {
                if (it.title.trim().equals(announcementId.trim(), ignoreCase = true)) {
                    Log.d("asdsad", "Navigating to detail for $announcementId")

                    navigateToAnnouncementDetail(
                        it.title,
                        it.content,
                        it.isImportant,
                        it.updateAt,
                        it.hasExternalLinks,
                        it.externalLinks,
                        it.externalLinksTitles,
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_setting_title),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        SettingHeader(
            text = stringResource(R.string.my_page_setting_item_announcement),
            painterResources = R.drawable.ic_announcement_tag
        )
        HorizontalDivider(
            color = Gray100,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        when (isAnnouncementState.announcementValidateResult) {
            AnnouncementResult.Success -> {
                AnnouncementItems(
                    isAnnouncementState = isAnnouncementState,
                    onClick = navigateToAnnouncementDetail
                )
                SettingContainer(
                    title = stringResource(R.string.my_page_setting_announcement_item_affiliate),
                    isImportant = false,
                    onClick = {})
                SettingContainer(
                    title = stringResource(R.string.my_page_setting_announcement_item_operation_information),
                    isImportant = false,
                    onClick = {})
                SettingContainer(
                    title = stringResource(R.string.my_page_setting_announcement_item_event_information),
                    isImportant = false,
                    onClick = {})
                SettingContainer(
                    title = stringResource(R.string.my_page_setting_announcement_item_closing_information),
                    isImportant = false,
                    onClick = {})
                SettingContainer(
                    title = stringResource(R.string.my_page_setting_announcement_item_operational_matters),
                    isImportant = false,
                    onClick = {})
            }

            AnnouncementResult.Empty -> {
                AnnouncementLoading()
            }

            AnnouncementResult.Error -> {}
        }
    }
}

@Composable
fun AnnouncementLoading() {
    Column(
        modifier = Modifier
            .background(Main1)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear))
        LottieAnimation(
            composition = composition,
        )
    }
}