package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
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
    imageSize: Int,
    characterRes: List<String>,
    updateSelectedCharacter: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { imageSize })
    val coroutineScope = rememberCoroutineScope()
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .clickableWithoutRipple(interactionSource,
                    onClick = {
                        coroutineScope.launch {
                            val previousPage = if (pagerState.currentPage - 1 < 0) {
                                pagerState.pageCount - 1
                            } else {
                                pagerState.currentPage - 1
                            }
                            pagerState.animateScrollToPage(previousPage)
                            updateSelectedCharacter(previousPage + 1)
                        }
                    }
                )
                .padding(top = 126.dp, bottom = 92.dp),
            painter = painterResource(id = R.drawable.btn_auth_pre_character),
            contentDescription = "pre_character_button"
        )
        Spacer(modifier = Modifier.weight(1f))
        HorizontalPager(
            modifier = Modifier
                .width(132.dp)
                .align(Alignment.Bottom)
                .height(296.dp),
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(characterRes[page])
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Bottom),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .clickableWithoutRipple(interactionSource, onClick = {
                    coroutineScope.launch {
                        val nextPage =
                            ((pagerState.currentPage + 1) % imageSize).coerceAtMost(
                                pagerState.pageCount - 1
                            )
                        pagerState.animateScrollToPage(nextPage)
                        updateSelectedCharacter(nextPage + 1)
                    }
                })
                .padding(top = 126.dp, bottom = 92.dp),
            painter = painterResource(id = R.drawable.btn_auth_next_character),
            contentDescription = "next_character_button"
        )
    }
    Spacer(
        modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
    )
    SetCharacterIndicator(imageSize, pagerState)
}