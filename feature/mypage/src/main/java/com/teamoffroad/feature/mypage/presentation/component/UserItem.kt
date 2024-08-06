package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun UserNickname(nickname: String) {
    Column {
        Text(
            style = OffroadTheme.typography.bothSubtitle3,
            color = Main2,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    append(nickname)
                }
                append(stringResource(id = R.string.mypage_explorer_suffix))
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Row {
            Text(
                text = stringResource(id = R.string.mypage_leave_for_an_adventure),
                color = Main2,
                style = OffroadTheme.typography.bothSubtitle3
            )
            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
            Image(
                painter = painterResource(id = R.drawable.img_flag),
                contentDescription = "flag"
            )
        }
    }
}

@Composable
fun UserAdventureInfo() {
    Column {
        Surface(
            color = Main1,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Column {
                Spacer(modifier = Modifier.padding(vertical = 9.dp))
                Row {
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                    UserImage()
                    Spacer(modifier = Modifier.padding(horizontal = 15.dp))
                    UserInfo()
                }
                Spacer(modifier = Modifier.padding(vertical = 9.dp))
            }

        }
        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
                .background(Main1),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.img_line),
                contentDescription = "line",
                alignment = Alignment.Center,
            )
        }
        Surface(
            color = Main1,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Column{
                Spacer(modifier = Modifier.padding(vertical = 7.dp))
                Row{
                    Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)) {
                        QuestAchievement()
                    }
                    Spacer(modifier = Modifier
                        .height(18.dp)
                        .width(1.dp)
                        .background(Contents2)
                    )
                    Box(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)) {
                        VisitedPlace()
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 7.dp))
            }
        }
    }
}

@Composable
fun UserInfo() {
    Column {
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        UserAdventureDate()
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        UserEmblem()
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
    }
}

@Composable
fun UserImage() {
    Image(
        painter = painterResource(id = R.drawable.test_img_user_home),
        contentDescription = "user image"
    )
}

@Composable
fun UserAdventureDate() {
    Text(
        style = OffroadTheme.typography.btnSmall,
        color = Main2,
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontFamily = PretendardBold,
                    fontWeight = FontWeight.Bold,
                    color = Sub2
                )
            ) {
                append("6887")
            }
            append(stringResource(id = R.string.mypage_adventure_date))
        }
    )
}

@Composable
fun UserEmblem() {
    Box {
        Image(
            painterResource(id = R.drawable.img_emblem_tag),
            contentDescription = "emblem tag"
        )
        Text(
            text = "상수 고수 악수 박수",
            color = Sub,
            style = OffroadTheme.typography.textContents,
            modifier = Modifier
                .padding(start = 10.dp, top = 6.dp, bottom = 6.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun QuestAchievement() {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = OffroadTheme.typography.textContentsSmall,
            color = Main2,
            text = buildAnnotatedString {
                append(stringResource(id = R.string.mypage_quest_achievement_amount))
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                        color = Sub2
                    )
                ) {
                    append("3")
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun VisitedPlace() {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = OffroadTheme.typography.textContentsSmall,
            color = Main2,
            text = buildAnnotatedString {
                append(stringResource(id = R.string.mypage_visit_place_amount))
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                        color = Sub2
                    )
                ) {
                    append("34")
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}