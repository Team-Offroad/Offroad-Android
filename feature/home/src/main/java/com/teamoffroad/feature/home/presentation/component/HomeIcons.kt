package com.teamoffroad.feature.home.presentation.component

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.ErrorNew
import com.teamoffroad.offroad.feature.home.R

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeIcons(
    context: Context,
    imageUrl: String,
    navigateToGainedCharacter: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.padding(top = 80.dp, end = 20.dp)
    ) {
        Column(modifier = Modifier.width(48.dp)) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_chat),
                    contentDescription = "chat",
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .padding(top = 6.dp, end = 6.dp)
                            .size(8.dp)
                    ) {
                        drawCircle(
                            color = ErrorNew,
                            style = Fill
                        )
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_home_upload),
                contentDescription = "upload",
            )
            Image(
                painter = painterResource(id = R.drawable.ic_home_change),
                contentDescription = "change",
                modifier = Modifier.clickableWithoutRipple { navigateToGainedCharacter() }
            )
        }
    }
}
