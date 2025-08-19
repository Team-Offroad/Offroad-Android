package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.mypage.presentation.component.AcquireCharacter
import com.teamoffroad.feature.mypage.presentation.component.AcquireCoupon
import com.teamoffroad.feature.mypage.presentation.component.AcquireEmblem
import com.teamoffroad.feature.mypage.presentation.component.UserAdventureInfo
import com.teamoffroad.feature.mypage.presentation.component.UserDiary
import com.teamoffroad.feature.mypage.presentation.component.UserNickname
import com.teamoffroad.feature.mypage.presentation.component.UserSettings

@Composable
internal fun MyPageScreen(
    navigateToGainedCharacter: () -> Unit,
    navigateToGainedCoupon: (String) -> Unit,
    navigateToGainedEmblems: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToDiary: (Boolean, String) -> Unit,
    myPageViewModel: MyPageViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        myPageViewModel.apply {
            getCharacterName()
            getMyPageUser()
            getDiaryCheckLatest()
        }
    }

    val snackBarHostState = remember { SnackbarHostState() }
    val errorMessage = myPageViewModel.errorMessage.collectAsStateWithLifecycle().value
    val newDiaryExist = myPageViewModel.newDiaryExist.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()
    if (errorMessage.isNotBlank()) {
        LaunchedEffect(snackBarHostState) { snackBarHostState.showSnackbar(message = errorMessage) }
        myPageViewModel.updateErrorMessage("")
    }

    Box(
        modifier = Modifier
            .background(ListBg)
            .padding(horizontal = 24.dp)
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .actionBarPadding()
                .verticalScroll(state = scrollState)
                .fillMaxSize()
                .padding(top = 42.dp)
        ) {
            UserNickname(
                modifier = Modifier.padding(bottom = 13.dp),
                nickname = myPageViewModel.myPageUser.collectAsStateWithLifecycle().value.nickname,
            )
            UserAdventureInfo(
                modifier = Modifier.padding(vertical = 8.dp),
                user = myPageViewModel.myPageUser.collectAsStateWithLifecycle().value,
            )
            UserDiary(
                modifier = Modifier.padding(top = 8.dp),
                navigateToUserDiary = {
                    navigateToDiary(
                        !newDiaryExist,
                        myPageViewModel.characterName.value
                    )
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(150f / 124f)
                ) {
                    AcquireCharacter(
                        navigateToGainedCharacter
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(150f / 124f)
                ) {
                    AcquireCoupon(
                        navigateToAcquireCoupon = { navigateToGainedCoupon(myPageViewModel.characterName.value) }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(150f / 124f)
                ) {
                    AcquireEmblem(
                        navigateToGainedEmblems
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(150f / 124f)
                ) {
                    UserSettings(
                        navigateToSetting
                    )
                }
            }
            Spacer(modifier = Modifier.height(106.dp))
        }
    }
}