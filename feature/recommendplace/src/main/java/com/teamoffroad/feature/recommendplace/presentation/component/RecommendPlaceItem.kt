package com.teamoffroad.feature.recommendplace.presentation.component

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SettingSetting
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState
import com.teamoffroad.offroad.feature.explore.R
import java.net.URLEncoder

@Composable
fun RecommendPlaceItem(
    modifier: Modifier = Modifier,
    recommendPlaceModel: PlaceRecommendationsUiState.RecommendationsUiState,
    isMap: Boolean = false,
) {
    val context = LocalContext.current

    Column(modifier) {
        Row {
            PlaceTagItem(
                text = recommendPlaceModel.placeCategory.krName,
                textColor = Sub2,
                backgroundColor = NametagInactive,
            )
            PlaceTagItem(
                modifier = Modifier.padding(start = 6.dp),
                text = recommendPlaceModel.placeArea,
                textColor = Sub,
                backgroundColor = NametagInactive,
            )
        }
        Text(
            text = recommendPlaceModel.name,
            style = OffroadTheme.typography.tooltipTitle,
            color = Main2,
            modifier = Modifier.padding(top = 12.dp)
        )
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = recommendPlaceModel.address,
                style = OffroadTheme.typography.hint,
                color = Gray400,
            )
            if (isMap) {
                Image(
                    painter = painterResource(id = R.drawable.ic_explore_external_link),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .clickableWithoutRipple {
                            val geoUri = Uri.parse("nmap://place?name=${URLEncoder.encode(recommendPlaceModel.name, "UTF-8")}&lat=${recommendPlaceModel.latitude}&lng=${recommendPlaceModel.longitude}&appname=${context.packageName}")
                            val intent = Intent(Intent.ACTION_VIEW, geoUri).apply {
                                setPackage("com.nhn.android.nmap")
                            }

                            try {
                                context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                val playStoreIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=com.nhn.android.nmap")
                                )
                                context.startActivity(playStoreIntent)
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun RecommendPlaceExtraItem(
    recommendPlaceModel: PlaceRecommendationsUiState.RecommendationsUiState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
            .background(color = BoxInfo, shape = RoundedCornerShape(9.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AdaptationImage(
            imageUrl = recommendPlaceModel.categoryImageUrl,
            modifier = Modifier
                .height(18.dp)
                .widthIn(max = 24.dp),
            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = recommendPlaceModel.shortIntroduction,
            color = Main2,
            style = OffroadTheme.typography.textContents,
            modifier = Modifier
                .padding(start = 6.dp)
                .padding(vertical = 2.dp)
                .weight(1f),
        )
    }
}

@Composable
private fun PlaceTagItem(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(
        text = text,
        style = OffroadTheme.typography.textContentsSmall,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        color = textColor
    )
}
