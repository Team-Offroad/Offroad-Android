package com.teamoffroad.core.common.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun saveBitmapToExternal(context: Context, bitmap: Bitmap): Uri? {
    val file = File(context.getExternalFilesDir(null), "shared_image.png")
    file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}