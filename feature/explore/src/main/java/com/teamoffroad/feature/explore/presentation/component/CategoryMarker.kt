package com.teamoffroad.feature.explore.presentation.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory

@Composable
fun CategoryMarker(
    category: PlaceCategory,
    size: Dp = 24.dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .size(size)
                .shadow(
                    elevation = 4.dp,
                    shape = CircleShape,
                    ambientColor = Black25,
                    spotColor = Black25,
                )
                .background(category.color(), shape = CircleShape)
                .border(width = 1.dp, color = Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .size(size / 4)
                    .background(Color.White, shape = CircleShape),
        )
    }
}

private fun PlaceCategory.color(): Color =
    when (this) {
        PlaceCategory.CAFFE -> Color(0xFFFFDF9B)
        PlaceCategory.PARK -> Color(0xFFBCE358)
        PlaceCategory.RESTAURANT -> Color(0xFFFF8670)
        PlaceCategory.CULTURE -> Color(0xFFFF6CC0)
        PlaceCategory.SPORT -> Color(0xFF729EFF)
        PlaceCategory.NONE -> Color(0xFFBDBDBD)
    }

fun getCategoryOverlayImage(
    context: Context,
    category: PlaceCategory,
    sizeDp: Dp = 24.dp,
): Bitmap {
    val density = context.resources.displayMetrics.density
    val sizePx = (sizeDp.value * density).toInt()
    val bitmap = createBitmap(sizePx, sizePx)
    val canvas = Canvas(bitmap)

    val radius = sizePx / 2f

    // 배경 원
    val fillPaint =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = category.color().toArgb()
            style = Paint.Style.FILL
        }
    canvas.drawCircle(radius, radius, radius, fillPaint)

    // 안쪽 점
    val dotPaint =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.White.toArgb()
            style = Paint.Style.FILL
        }
    canvas.drawCircle(radius, radius, radius / 4f, dotPaint)

    // 바깥쪽 테두리
    val borderPaint =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.White.toArgb()
            style = Paint.Style.STROKE
            strokeWidth = density * 1f // 1dp 테두리
        }
    canvas.drawCircle(radius, radius, radius - borderPaint.strokeWidth / 2, borderPaint)

    return bitmap
}

@Preview
@Composable
fun CategoryMarkerPreview() {
    CategoryMarker(
        category = PlaceCategory.CAFFE,
        size = 24.dp,
    )
}
