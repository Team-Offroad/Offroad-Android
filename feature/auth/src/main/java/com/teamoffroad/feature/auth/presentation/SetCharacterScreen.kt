package com.teamoffroad.feature.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.theme.Brown
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.feature.auth.presentation.component.SetCharacterDialog
import com.teamoffroad.feature.auth.presentation.component.SetCharacterIndicator
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun SetCharacterScreen(
    navigateToHome: () -> Unit,
    viewModel: AuthViewModel,
) {
    val showDialog = mutableStateOf(false)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            Column(
                modifier = Modifier
                    .background(Sub),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 104.dp, bottom = 30.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    text = "동료 캐릭터 선택",
                    color = Main2,
                    style = OffroadTheme.typography.title,
                )
                ShowSetCharacterPager()
                Spacer(modifier = Modifier.padding(vertical = 14.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Main1),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "오푸",
                    style = OffroadTheme.typography.title
                )
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(
                    modifier = Modifier
                        .width(260.dp)
                        .height(1.dp)
                        .background(Brown.copy(alpha = 0.25f))
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "오푸는 어쩌고 저쩌고 성격을 가진 귀여운 \n" +
                            "어쩌고 저쩌고 들어간다면 이렇게 들어갑니다.\n" +
                            "세 줄까지 이 정도. 이렇게 저렇게 이렇게 짝짝.",
                    style = OffroadTheme.typography.textRegular,
                    color = Main2
                )
                Spacer(modifier = Modifier.height(30.dp))
                OffroadBasicBtn(
                    modifier = Modifier
                        .width(312.dp)
                        .height(50.dp),
                    onClick = navigateToHome,
                    isActive = true,
                    text = "선택",
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ShowSetCharacterPager() {
    val imageSize = 3
    val pagerState = rememberPagerState(pageCount = { imageSize })
    val coroutineScope = rememberCoroutineScope()

    //가짜 데이터
    val imageResourceIds = listOf(
        R.drawable.ic_auth_logo,
        R.drawable.ic_auth_google_logo,
        R.drawable.ic_auth_kakao_logo
    )
    Row(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .clickable {
                    coroutineScope.launch {
                        val previousPage = if (pagerState.currentPage - 1 < 0) {
                            pagerState.pageCount - 1
                        } else {
                            pagerState.currentPage - 1
                        }
                        pagerState.animateScrollToPage(previousPage)
                    }
                }
                .padding(top = 126.dp, bottom = 92.dp),
            painter = painterResource(id = R.drawable.btn_pre_character),
            contentDescription = "pre_character_button"
        )
        Spacer(modifier = Modifier.weight(1f))
        HorizontalPager(
            modifier = Modifier
                .width(132.dp)
                .height(250.dp),
            state = pagerState
        ) { page ->
            val image: Painter = painterResource(id = imageResourceIds[page])
            Image(
                painter = image,
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .clickable {
                    coroutineScope.launch {
                        val nextPage =
                            ((pagerState.currentPage + 1) % imageSize).coerceAtMost(
                                pagerState.pageCount - 1
                            )
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
                .padding(top = 126.dp, bottom = 92.dp),
            painter = painterResource(id = R.drawable.btn_next_character),
            contentDescription = "next_character_button"
        )
    }
    Spacer(modifier = Modifier.padding(vertical = 20.dp))
    SetCharacterIndicator(imageSize, pagerState)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowSetCharacterDialog() {
    val showDialog = mutableStateOf(false)
    var selectedCharacterIdx = remember { mutableStateOf<String?>("초보 모험가") }

    SetCharacterDialog(
        characterName = "오푸",
        showDialog = showDialog,
        onClickCancel = {
            showDialog.value = false
        },
        onCharacterChange = { idx ->
            selectedCharacterIdx.value = idx.toString()
        }
    )
}