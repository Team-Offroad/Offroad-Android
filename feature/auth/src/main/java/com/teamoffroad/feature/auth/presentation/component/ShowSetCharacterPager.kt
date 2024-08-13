package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.launch

@Composable
fun ShowSetCharacterPager(
    pagerState: PagerState,
    imageSize: Int,
    characterRes: List<String>,
    updateSelectedCharacter: (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    LaunchedEffect(true) {
        pagerState.scrollToPage(Int.MAX_VALUE / 2)
    }
    val context = LocalContext.current
    LaunchedEffect(pagerState.currentPage) {
        updateSelectedCharacter(pagerState.currentPage % imageSize + 1)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            state = pagerState,
            userScrollEnabled = true,
        ) { page ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(characterRes[page % imageSize])
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize()
                )
            }
        }
        Image(
            modifier = Modifier
                .padding(start = 24.dp)
                .clickableWithoutRipple(interactionSource,
                    onClick = {
                        coroutineScope.launch {
                            val previousPage = if (pagerState.currentPage - 1 < 0) {
                                pagerState.pageCount - 1
                            } else {
                                pagerState.currentPage - 1
                            }
                            pagerState.animateScrollToPage(previousPage)
                        }
                    }
                )
                .padding(top = 126.dp, bottom = 92.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(id = R.drawable.btn_auth_pre_character),
            contentDescription = "pre_character_button"
        )
        Image(
            modifier = Modifier
                .padding(end = 24.dp)
                .clickableWithoutRipple(interactionSource, onClick = {
                    coroutineScope.launch {
                        val nextPage =
                            ((pagerState.currentPage + 1)).coerceAtMost(
                                pagerState.pageCount - 1
                            )
                        pagerState.animateScrollToPage(nextPage)
                    }
                })
                .padding(top = 126.dp, bottom = 92.dp)
                .align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.btn_auth_next_character),
            contentDescription = "next_character_button"
        )
    }
}