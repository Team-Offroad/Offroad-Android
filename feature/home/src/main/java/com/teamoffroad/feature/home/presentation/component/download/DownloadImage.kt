package com.teamoffroad.feature.home.presentation.component.download

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.teamoffroad.feature.home.presentation.model.DownloadResult
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun downloadImage(
    context: Context,
    imageUrl: String,
    scope: CoroutineScope
) {
    scope.launch(Dispatchers.IO) {
        val result = loadImage(context, imageUrl)
        if (result is DownloadResult.Success) {
            val bitmap = result.data
            val saveResult = saveImageToMediaStore(context, bitmap)
            if (saveResult is DownloadResult.Error) {
                showToast(context, saveResult.message)
            } else {
                showToast(context, context.getString(R.string.success_download_image))
            }
        } else if (result is DownloadResult.Error) {
            showToast(context, context.getString(R.string.fail_download_image))
        }
    }
}

private suspend fun loadImage(
    context: Context,
    imageUrl: String
): DownloadResult<Bitmap> {
    return withContext(Dispatchers.IO) {
        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        when (val result = imageLoader.execute(request)) {
            is SuccessResult -> {
                val drawable = result.drawable
                val bitmap = (drawable as? BitmapDrawable)?.bitmap
                if (bitmap != null) {
                    DownloadResult.Success(bitmap)
                } else {
                    DownloadResult.Error(context.getString(R.string.fail_convert_image_to_bitmap))
                }
            }
            is ErrorResult -> DownloadResult.Error("${result.throwable.message}")
            else -> DownloadResult.Error(context.getString(R.string.fail_request))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun saveImageToMediaStore(context: Context, bitmap: Bitmap): DownloadResult<Unit> {
    val contentValues = ContentValues().apply {
        put(
            MediaStore.Images.Media.DISPLAY_NAME,
            "downloaded_image_${System.currentTimeMillis()}.png"
        )
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    return uri?.let {
        context.contentResolver.openOutputStream(it)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            DownloadResult.Success(Unit)
        } ?: DownloadResult.Error(context.getString(R.string.fail_create_outputstream))
    } ?: DownloadResult.Error(context.getString(R.string.fail_create_uri))
}

private suspend fun showToast(context: Context, message: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
