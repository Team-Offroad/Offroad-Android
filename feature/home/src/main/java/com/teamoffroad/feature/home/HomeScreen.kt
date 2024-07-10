package com.teamoffroad.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.teamoffroad.core.designsystem.theme.Sub
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
        ConstraintLayout {
            val stamp = createRef()
            Image(
                painter = painterResource(id = R.drawable.img_home_stamp),
                contentDescription = "stamp",
                modifier = Modifier
                    .constrainAs(stamp) {
                        top.linkTo(parent.top, margin = 28.dp)
                        end.linkTo(parent.end, margin = (-36).dp)
                    }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                TextNickname("비포장도로")
                Spacer(modifier = Modifier.padding(12.dp))
                TextCharacterName("오푸")

                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (explorer, download, upload, change) = createRefs()

                    Image(
                        painter = painterResource(id = R.drawable.img_home_explorer),
                        contentDescription = "explorer",
                        modifier = Modifier
                            .constrainAs(explorer) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_home_download),
                        contentDescription = "download",
                        modifier = Modifier
                            .constrainAs(download) {
                                end.linkTo(parent.end, margin = 30.dp)
                            },
                        tint = Color.Unspecified
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_home_upload),
                        contentDescription = "upload",
                        modifier = Modifier
                            .constrainAs(upload) {
                                end.linkTo(download.end)
                                top.linkTo(download.bottom)
                            },
                        tint = Color.Unspecified
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_home_change),
                        contentDescription = "change",
                        modifier = Modifier
                            .constrainAs(change) {
                                end.linkTo(upload.end)
                                top.linkTo(upload.bottom)
                            },
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.padding(top = 18.dp))

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(
                            color = Sub,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(vertical = 6.dp)
                ) {
                    val (title, changeIcon) = createRefs()
                    //PopupTagSub(modifier = Modifier)
                    Text(
                        text = "초보 모험가",
                        color = White,
                        style = OffroadTheme.typography.subtitle2Semibold,
                        modifier = Modifier
                            .constrainAs(title) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_home_change_title),
                        contentDescription = "change title",
                        modifier = Modifier
                            .constrainAs(changeIcon) {
                                end.linkTo(parent.end, margin = 18.dp)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                        tint = Color.Unspecified
                    )
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
            Spacer(modifier = Modifier.padding(top = 34.dp))
        }

    }
}

@Composable
fun ProgressBar(data: HomeProgressBarData) {
    when(data.type) {
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
        modifier = Modifier.padding(start = 24.dp),
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
            .padding(start = 24.dp)
            .background(
                color = CharacterName,
                shape = RoundedCornerShape(6.dp)
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

