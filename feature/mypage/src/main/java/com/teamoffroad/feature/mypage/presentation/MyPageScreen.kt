package com.teamoffroad.feature.mypage.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.component.AcquireCharacter
import com.teamoffroad.feature.mypage.presentation.component.AcquireCoupon
import com.teamoffroad.feature.mypage.presentation.component.AcquireEmblem
import com.teamoffroad.feature.mypage.presentation.component.UserSettings
import com.teamoffroad.feature.mypage.presentation.component.UserAdventureInfo
import com.teamoffroad.feature.mypage.presentation.component.UserNickname
import kotlinx.coroutines.coroutineScope

@Composable
internal fun MyPageScreen(
    navigateToGainedCharacter: () -> Unit,
    navigateToGainedCoupon: () -> Unit,
    myPageViewModel: MyPageViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        myPageViewModel.getMyPageUser()
    }

    val snackBarHostState = remember { SnackbarHostState() }
    val errorMessage = myPageViewModel.errorMessage.collectAsStateWithLifecycle().value
    if (errorMessage.isNotBlank()) {
        LaunchedEffect(snackBarHostState) { snackBarHostState.showSnackbar(message = errorMessage) }
        myPageViewModel.updateErrorMessage("")
    }

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
            UserNickname(myPageViewModel.myPageUser.collectAsStateWithLifecycle().value.nickname)
            Spacer(modifier = Modifier.padding(vertical = 13.dp))
            UserAdventureInfo(
                myPageViewModel.myPageUser.collectAsStateWithLifecycle().value
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(modifier = Modifier.weight(1f)) { AcquireCharacter(navigateToGainedCharacter) }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Box(modifier = Modifier.weight(1f)) { AcquireCoupon(navigateToGainedCoupon) }
            }
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(modifier = Modifier.weight(1f)) { AcquireEmblem() }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Box(modifier = Modifier.weight(1f)) { UserSettings() }
            }
            Spacer(modifier = Modifier.padding(vertical = 28.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    OffroadTheme {
        MyPageScreen({}, {})
    }
}