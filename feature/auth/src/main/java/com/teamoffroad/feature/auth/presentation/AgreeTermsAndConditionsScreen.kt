package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsItem
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBar
import com.teamoffroad.feature.auth.presentation.component.AgreeTermsAndConditionsTopBarAllAgreeBox
import com.teamoffroad.feature.auth.presentation.component.OffroadBasicBtn

@Composable
internal fun AgreeTermsAndConditionsScreen(
    navigateToSetNicknameScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Main1)
    ) {
        AgreeTermsAndConditionsTopBar(modifier.padding(bottom = 24.dp))
        AgreeTermsAndConditionsTopBarAllAgreeBox(false, modifier = Modifier.padding(bottom = 22.dp))
        AgreeTermsAndConditionsItem(text = "서비스 이용 약관", isChecked = true, onClick = {})
        AgreeTermsAndConditionsItem(text = "개인정보수집/이용 동의", isChecked = true, onClick = {})
        AgreeTermsAndConditionsItem(text = "위치기반 서비스 이용약관", isChecked = false, onClick = {})
        AgreeTermsAndConditionsItem(text = "마케팅 정보 수신 동의", isChecked = false, onClick = {})
        Spacer(modifier = Modifier.weight(1f))
        OffroadBasicBtn(
            text = "다음",
            isActive = false,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 72.dp)
                .height(50.dp),
        ) {

        }
    }
}