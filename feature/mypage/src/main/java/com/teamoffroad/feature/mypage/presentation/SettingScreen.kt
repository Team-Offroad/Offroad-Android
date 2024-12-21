package com.teamoffroad.feature.mypage.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naver.maps.map.app.LegalNoticeActivity
import com.naver.maps.map.app.OpenSourceLicenseActivity
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsDialog
import com.teamoffroad.feature.mypage.presentation.component.LogoutDialog
import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.feature.mypage.presentation.component.SettingItems
import com.teamoffroad.feature.mypage.presentation.component.WithDrawDialog
import com.teamoffroad.feature.mypage.presentation.model.SettingItem
import com.teamoffroad.offroad.feature.mypage.R
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    navigateToAnnouncement: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToSupport: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val isSettingUiState by viewModel.settingUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var snackBarShowState by remember { mutableStateOf(false) }
    val currentDateTime = remember { LocalDateTime.now() }
    val formatter = remember {
        DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm", Locale.getDefault())
    }

    LaunchedEffect(snackBarShowState) {
        if (snackBarShowState) {
            coroutineScope.launch {
                val snackBar = snackBarHostState.showSnackbar(
                    message = when (isSettingUiState.marketingAgree == true) {
                        true -> context.getString(
                            R.string.my_page_setting_marketing_agree,
                            currentDateTime.format(formatter)
                        )

                        false -> context.getString(
                            R.string.my_page_setting_marketing_disagree,
                            currentDateTime.format(formatter)
                        )
                    },
                    actionLabel = context.getString(R.string.my_page_setting_marketing_exit),
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

    Box(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .background(Main1)
            .actionBarPadding()
    ) {
        Column {
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
            SettingItems(
                settingItemList = listOf(
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_announcement),
                        isImportant = false,
                        onClick = navigateToAnnouncement
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_play_guide),
                        isImportant = false,
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://tan-antlion-a47.notion.site/105120a9d80f80cea574f7d62179bfa8")
                            )
                            context.startActivity(intent)
                        }),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_service_term),
                        isImportant = false,
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://tan-antlion-a47.notion.site/90c70d8bf0974b37a3a4470022df303d")
                            )
                            context.startActivity(intent)
                        }
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_personal_information),
                        isImportant = false,
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://tan-antlion-a47.notion.site/105120a9d80f80739f54fa78902015d7")
                            )
                            context.startActivity(intent)
                        }
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_naver_map_support),
                        isImportant = false,
                        onClick = { navigateToNaverMapSupport(context) }
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_marketing_agree),
                        isImportant = false,
                        onClick = { viewModel.changeDialogState(SettingDialogState.MarketingVisible) }
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_customer_support),
                        isImportant = false,
                        onClick = { navigateToSupport() }
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_logout),
                        isImportant = false,
                        onClick = { viewModel.changeDialogState(SettingDialogState.LogoutVisible) }
                    ),
                    SettingItem(
                        title = stringResource(R.string.my_page_setting_item_withdraw),
                        isImportant = false,
                        onClick = { viewModel.changeDialogState(SettingDialogState.WithDrawVisible) }
                    ),
                )
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        SnackbarHost(
            modifier = Modifier
                .align(Alignment.BottomCenter)
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
            }
        )

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
            }
        )
    }
}

private fun navigateToNaverMapSupport(context: Context) {
    val openSourceLicenseIntent = Intent(context, OpenSourceLicenseActivity::class.java)
    context.startActivity(openSourceLicenseIntent)

    val legalNoticeIntent = Intent(context, LegalNoticeActivity::class.java)
    context.startActivity(legalNoticeIntent)
}