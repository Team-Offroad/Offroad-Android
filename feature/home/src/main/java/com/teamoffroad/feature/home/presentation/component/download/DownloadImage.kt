package com.teamoffroad.feature.home.presentation.component.download

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.teamoffroad.feature.home.presentation.model.DownloadResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

fun downloadImage(context: Context, url: String, scope: CoroutineScope) {
    scope.launch(Dispatchers.IO) {
        val result = loadImage(context, url)
        if (result is DownloadResult.Success) {
            val bitmap = result.data
            val saveResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                saveImageToMediaStore(context, bitmap)
            } else {
                saveImageToExternalStorage(context, bitmap)
            }
            if (saveResult is DownloadResult.Error) {
                showToast(context, saveResult.message)
            }
        } else if (result is DownloadResult.Error) {
            showToast(context, result.message)
        }
    }
}

private suspend fun loadImage(context: Context, url: String): DownloadResult<Bitmap> {
    return withContext(Dispatchers.IO) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        when (val result = imageLoader.execute(request)) {
            is SuccessResult -> DownloadResult.Success(result.drawable.toBitmap())
            is ErrorResult -> DownloadResult.Error("이미지 다운로드 실패: ${result.throwable.message}")
            else -> DownloadResult.Error("이미지 다운로드 실패: 요청 실패")
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

    val uri =
        context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    return uri?.let {
        context.contentResolver.openOutputStream(it)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            DownloadResult.Success(Unit)
        } ?: DownloadResult.Error("이미지 저장 실패: OutputStream 생성 실패")
    } ?: DownloadResult.Error("이미지 저장 실패: URI 생성 실패")
}

private fun saveImageToExternalStorage(context: Context, bitmap: Bitmap): DownloadResult<Unit> {
    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    if (!directory.exists()) {
        directory.mkdirs()
    }

    val file = File(directory, "downloaded_image_${System.currentTimeMillis()}.png")
    return FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        DownloadResult.Success(Unit)
    }.let { result ->
        if (result is DownloadResult.Success) result else DownloadResult.Error("이미지 저장 실패")
    }
}

private suspend fun showToast(context: Context, message: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
