package com.teamoffroad.feature.home.presentation.component.upload

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import com.caverock.androidsvg.SVG
import com.teamoffroad.feature.home.presentation.component.download.downloadImage
import com.teamoffroad.offroad.core.common.BuildConfig
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

suspend fun uploadImage(context: Context, imageUrl: String) {
    val uri = downloadAndConvertImage(context, imageUrl)
    if (uri != null) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, null))
    } else {
        showToast(context, context.getString(R.string.success_upload_image))
    }
}

suspend fun downloadAndConvertImage(context: Context, imageUrl: String): Uri? {
    return withContext(Dispatchers.IO) {
        try {
            val connection: HttpURLConnection = URL(imageUrl).openConnection() as HttpURLConnection
            connection.connect()

            val inputStream: InputStream = connection.inputStream

            val pictureDrawable =
                PictureDrawable(SVG.getFromInputStream(inputStream).renderToPicture())
            val bitmap = Bitmap.createBitmap(
                pictureDrawable.intrinsicWidth,
                pictureDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            canvas.drawPicture(pictureDrawable.picture)

            val file = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "shared_image.png"
            )
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.close()
            inputStream.close()

            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            showToast(context, context.getString(R.string.success_upload_image))
            null
        }
    }
}

private suspend fun showToast(context: Context, message: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}