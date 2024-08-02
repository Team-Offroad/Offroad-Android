package com.teamoffroad.feature.home.presentation.component.upload

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
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
            val imageLoader = ImageLoader.Builder(context)
                .components { add(SvgDecoder.Factory()) }
                .build()

            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .build()

            when (val result = imageLoader.execute(request)) {
                is SuccessResult -> {
                    val bitmap = (result.drawable as? BitmapDrawable)?.bitmap
                    if (bitmap != null) {
                        val file = File(
                            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            "shared_image.png"
                        )
                        FileOutputStream(file).use { outputStream ->
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        }

                        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
                    } else {
                        Log.d("Upload Image ErrorResult", context.getString(R.string.fail_convert_image_to_bitmap))
                        showToast(context, context.getString(R.string.fail_upload_image))
                        null
                    }
                }
                is ErrorResult -> {
                    Log.d("Upload Image ErrorResult", "${result.throwable.message}")
                    showToast(context, context.getString(R.string.fail_upload_image))
                    null
                }
                else -> {
                    showToast(context, context.getString(R.string.fail_request))
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showToast(context, context.getString(R.string.fail_upload_image))
            null
        }
    }
}
private suspend fun showToast(context: Context, message: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}