package com.teamoffroad.feature.home.presentation.component.download

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.drawToBitmap
import com.teamoffroad.feature.home.presentation.model.DownloadResult
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun captureAndSaveScreen(
    context: Context,
    view: View,
    scope: CoroutineScope
) {
    scope.launch(Dispatchers.IO) {
        val bitmap = view.drawToBitmap()
        val saveResult = saveImageToMediaStore(context, bitmap)
        if (saveResult is DownloadResult.Error) {
            showToast(context, saveResult.message)
        } else {
            showToast(context, context.getString(R.string.success_download_image))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun saveImageToMediaStore(context: Context, bitmap: Bitmap): DownloadResult<Unit> {
    val contentValues = ContentValues().apply {
        put(
            MediaStore.Images.Media.DISPLAY_NAME,
            "captured_image_${System.currentTimeMillis()}.png"
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