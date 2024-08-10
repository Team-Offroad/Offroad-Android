package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.component.AcquireCharacter
import com.teamoffroad.feature.mypage.presentation.component.AcquireCoupon
import com.teamoffroad.feature.mypage.presentation.component.AcquireEmblem
import com.teamoffroad.feature.mypage.presentation.component.Settings
import com.teamoffroad.feature.mypage.presentation.component.UserAdventureInfo
import com.teamoffroad.feature.mypage.presentation.component.UserNickname

@Composable
internal fun MyPageScreen(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState = myPageViewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .background(ListBg)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 52.dp)
            .padding(bottom = 74.dp)
            .navigationBarsPadding()
    ) {
        Column {
            OffroadActionBar(Color.Transparent)
            UserNickname(uiState.value.userData.nickname)
            Spacer(modifier = Modifier.padding(vertical = 13.dp))
            UserAdventureInfo(uiState.value.userData)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(modifier = Modifier.weight(1f)) { AcquireCharacter() }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Box(modifier = Modifier.weight(1f)) { AcquireCoupon() }
            }
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(modifier = Modifier.weight(1f)) { AcquireEmblem() }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Box(modifier = Modifier.weight(1f)) { Settings() }
            }
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
        }
    }
    OnBackButtonListener()
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    OffroadTheme {
        MyPageScreen()
    }
}