package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun UserNickname(
    modifier: Modifier = Modifier,
    nickname: String,
) {
    Column(
        modifier = modifier,
    ) {
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
                append(stringResource(id = R.string.my_page_explorer_suffix))
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Row {
            Text(
                text = stringResource(id = R.string.my_page_leave_for_an_adventure),
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
fun UserAdventureInfo(
    modifier: Modifier = Modifier,
    user: MyPageUser,
) {
    Column(
        modifier = modifier,
    ) {
        Surface(
            color = Main1,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(312f / 110f)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Row(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                UserImage(user.characterImageUrl)
                Spacer(modifier = Modifier.padding(horizontal = 15.dp))
                UserInfo(user.elapsedDay, user.currentEmblem)
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
                colorFilter = ColorFilter.tint(Stroke)
            )
        }
        Surface(
            color = Main1,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(312f / 40f)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Column {
                Spacer(modifier = Modifier.padding(vertical = 7.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    ) {
                        QuestAchievement(user.completeQuestCount)
                    }
                    Spacer(
                        modifier = Modifier
                            .height(18.dp)
                            .width(1.dp)
                            .background(Contents2)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    ) {
                        VisitedPlace(user.visitedPlaceCount)
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 7.dp))
            }
        }
    }
}

@Composable
fun UserInfo(date: Int, emblem: String) {
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            UserAdventureDate(date)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            UserEmblem(emblem)
        }
    }
}

@Composable
fun UserImage(
    characterImageUrl: String,
    shape: RoundedCornerShape = CircleShape,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier,
    borderColor: Color = Main1,
) {
//    Image(
//        painter = painterResource(id = R.drawable.test_img_user_home),
//        contentDescription = "user image"
//    )

    AdaptationImage(
        imageUrl = characterImageUrl,
        contentScale = contentScale,
        modifier = modifier
            .aspectRatio(78f / 78f)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                shape = shape,
                color = borderColor
            )
    )
}

@Composable
fun UserAdventureDate(date: Int) {
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
                append(date.toString())
            }
            append(stringResource(id = R.string.my_page_adventure_date))
        }
    )
}

@Composable
fun UserEmblem(emblem: String) {
    Box {
        Image(
            painterResource(id = R.drawable.img_emblem_tag),
            contentDescription = "emblem tag"
        )
        Text(
            text = emblem,
            color = Sub,
            style = OffroadTheme.typography.textContents,
            modifier = Modifier
                .padding(start = 10.dp, top = 6.dp, bottom = 6.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun QuestAchievement(achieveQuest: Int) {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = OffroadTheme.typography.textContentsSmall,
            color = Main2,
            text = buildAnnotatedString {
                append(stringResource(id = R.string.my_page_quest_achievement_amount))
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                        color = Sub2
                    )
                ) {
                    append(achieveQuest.toString())
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun VisitedPlace(visitPlace: Int) {
    Row {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = OffroadTheme.typography.textContentsSmall,
            color = Main2,
            text = buildAnnotatedString {
                append(stringResource(id = R.string.my_page_visit_place_amount))
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                        color = Sub2
                    )
                ) {
                    append(visitPlace.toString())
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}