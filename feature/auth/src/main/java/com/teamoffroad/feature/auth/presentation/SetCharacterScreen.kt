package com.teamoffroad.feature.auth.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.teamoffroad.core.designsystem.component.OffroadBasicBtn
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Brown
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.auth.presentation.component.SetCharacterDialog
import com.teamoffroad.feature.auth.presentation.component.SetCharacterIndicator
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun SetCharacterScreen(
    navigateToHome: () -> Unit,
    viewModel: SetCharacterViewModel,
) {

    val characters by viewModel.characters.collectAsStateWithLifecycle()
    val selectedCharacter by viewModel.selectedCharacter.collectAsStateWithLifecycle()
    viewModel.getCharacters()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            Column(
                modifier = Modifier
                    .background(
                        when (selectedCharacter.id) {
                            1 -> Color(0xFFFFF4CC)
                            2 -> Color(0xFFFFE1C5)
                            3 -> Color(0xFFF9E5D2)
                            else -> White
                        }
                    ),
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
                if (characters.isNotEmpty()) {
                    ShowSetCharacterPager(
                        imageSize = characters.size,
                        characterRes = characters.map { it.characterBaseImageUrl },
                        viewModel::updateSelectedCharacter
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 14.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Main1),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = selectedCharacter.name,
                        style = OffroadTheme.typography.title,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(
                        modifier = Modifier
                            .width(260.dp)
                            .height(1.dp)
                            .background(Brown.copy(alpha = 0.25f))
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = selectedCharacter.description,
                        style = OffroadTheme.typography.textRegular,
                        color = Main2,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxHeight()
                            .weight(1f),
                    )
                }
                OffroadBasicBtn(
                    modifier = Modifier
                        .width(312.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally),
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
fun ShowSetCharacterPager(imageSize: Int, characterRes: List<String>, updateSelectedCharacter: (Int) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { imageSize })
    val coroutineScope = rememberCoroutineScope()
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

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
                .height(250.dp),
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            AsyncImage(
                model = characterRes[page],
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp),
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