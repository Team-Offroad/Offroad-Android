package com.teamoffroad.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.common.component.ContentsLocation
import com.teamoffroad.core.common.component.ContentsTitle
import com.teamoffroad.core.common.component.PopupTagActive
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.CharacterName
import com.teamoffroad.core.designsystem.theme.Contents1
import com.teamoffroad.core.designsystem.theme.Contents1GraphMain
import com.teamoffroad.core.designsystem.theme.Contents1GraphSub
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.OpticianSansRegular
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.home.R

@Composable
internal fun HomeScreen(
    padding: PaddingValues,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = Main1
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column {
                    TextNickname("비포장도로")
                    TextCharacterName("오푸")
                }

                Box {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_home_stamp),
                            contentDescription = "stamp",
                        )

                        Box(
                            contentAlignment = Alignment.TopEnd,
                            modifier = Modifier.padding(top = 64.dp, end = 28.dp)
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_home_download),
                                    contentDescription = "explorer",
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_home_upload),
                                    contentDescription = "explorer",
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_home_change),
                                    contentDescription = "explorer",
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 90.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_home_explorer),
                            contentDescription = "explorer"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(18.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                PopupTagActive(text = "초보 모험가")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_home_change_title),
                        contentDescription = "change title",
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(start = 24.dp))
                Surface(
                    color = Contents1,
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    ProgressBar(HomeProgressBarData("최근 진행한 퀘스트", "circle", 3, 4, "홍대입구 한바퀴"))
                }
                Spacer(modifier = Modifier.padding(start = 12.dp))
                Surface(
                    color = Contents2,
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(10.dp))
                ) {
                    ProgressBar(HomeProgressBarData("완료 임박 퀘스트", "linear", 7, 8, "도심 속 공원 탐방"))
                }
                Spacer(modifier = Modifier.padding(end = 24.dp))
            }
        }
    }
}

@Composable
fun ProgressBar(data: HomeProgressBarData) {
    when (data.type) {
        "circle" -> {
            Column {
                Row {
                    ContentsTitle(data.title)
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_home_recent_quest),
                        contentDescription = "recent quest",
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 14.dp))

                var recentQuestProgress by remember { mutableStateOf(0.8f) }

                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (main, text) = createRefs()

                    CircularProgressIndicator(
                        progress = { recentQuestProgress },
                        color = Contents1GraphMain,
                        trackColor = Contents1GraphSub,
                        strokeWidth = 8.dp,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier
                            .size(82.dp)
                            .constrainAs(main) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Text(
                        text = "3/4",
                        color = Main1,
                        style = OffroadTheme.typography.bothRecentNum,
                        modifier = Modifier
                            .constrainAs(text) {
                                start.linkTo(main.start)
                                end.linkTo(main.end)
                                top.linkTo(main.top)
                                bottom.linkTo(main.bottom)
                            }
                    )

                }

                Spacer(modifier = Modifier.padding(top = 12.dp))
                ContentsLocation(data.location)
                Spacer(modifier = Modifier.padding(bottom = 12.dp))
            }
        }

        "linear" -> {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    ContentsTitle(title = data.title)
                    Spacer(modifier = Modifier.padding(start = 4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_home_close_complete),
                        contentDescription = "recent quest",
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(top = 16.dp))

                ConstraintLayout {
                    val text = createRef()

                    Text(
                        textAlign = TextAlign.Center,
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontFamily = OpticianSansRegular,
                                    fontWeight = FontWeight.Normal,
                                    color = Sub4,
                                    fontSize = 62.sp
                                )
                            ) {
                                append(data.amount.toString())
                            }
                            withStyle(
                                SpanStyle(
                                    fontFamily = OpticianSansRegular,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 30.sp,
                                    color = Black25
                                )
                            ) {
                                append("/")
                                append(data.total.toString())
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(text) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                }

                Spacer(modifier = Modifier.padding(top = 8.dp))

                ConstraintLayout {
                    val progress = createRef()
                    var closeCompleteProgress by remember { mutableStateOf(0.8f) }

                    LinearProgressIndicator(
                        progress = { closeCompleteProgress },
                        trackColor = Black25,
                        color = Sub4,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier
                            .height(8.dp)
                            .padding(end = 10.dp)
                            .constrainAs(progress) {
                                start.linkTo(parent.start, margin = (-3).dp)
                            }
                    )
                }

                Spacer(modifier = Modifier.padding(top = 20.dp))
                ContentsLocation(data.location)
                Spacer(modifier = Modifier.padding(bottom = 12.dp))
            }
        }
    }
}

@Composable
fun TextNickname(nickname: String) {
    Text(
        modifier = Modifier.padding(start = 24.dp, top = 32.dp),
        style = OffroadTheme.typography.subtitleReg,
        text = buildAnnotatedString {
            append("모험가 ")
            withStyle(
                SpanStyle(
                    fontFamily = PretendardBold,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(nickname)
            }
            append("님")
        }
    )
}

@Composable
fun TextCharacterName(name: String) {
    Text(
        style = OffroadTheme.typography.subtitle2Semibold,
        text = name,
        modifier = Modifier
            .padding(start = 24.dp, top = 12.dp)
            .background(
                color = CharacterName,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = Black15
            )
            .padding(horizontal = 16.dp)
            .padding(vertical = 6.dp),
        color = White
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OffroadTheme {
        HomeScreen(padding = PaddingValues())
    }
}

