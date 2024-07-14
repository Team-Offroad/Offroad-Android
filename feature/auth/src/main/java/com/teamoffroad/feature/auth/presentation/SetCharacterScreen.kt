package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import kotlinx.coroutines.launch

@Composable
internal fun SetCharacterScreen(
    navigateToHome: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 10 })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Sub,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier
                    .padding(top = 66.dp),
                text = "동료 캐릭터 선택",
                color = Main2,
                style = OffroadTheme.typography.title,
            )
            HorizontalPager(state = pagerState) { page ->
                Text(
                    text = "page: $page",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            val coroutineScope = rememberCoroutineScope()
            Button(onClick = {
                coroutineScope.launch {
                    // Call scroll to on pagerState
                    pagerState.animateScrollToPage(5)
                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Jump to Page 5")
            }

            Button(onClick = navigateToHome) {
                Text("sadasd")
            }
        }
    }
}