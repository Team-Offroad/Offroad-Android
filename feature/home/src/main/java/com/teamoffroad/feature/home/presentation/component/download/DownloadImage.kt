package com.teamoffroad.feature.home.presentation.component.download

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun downloadImage(context: Context, url: String, scope: CoroutineScope) {
    scope.launch(Dispatchers.IO) {
        try {
            val imageLoader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(url)
                .build()

            val result = imageLoader.execute(request)

            if (result is SuccessResult) {
                val bitmap = result.drawable.toBitmap()

                // MediaStore를 통해 이미지 저장
                val contentValues = ContentValues().apply {
                    put(
                        MediaStore.Images.Media.DISPLAY_NAME,
                        "downloaded_image_${System.currentTimeMillis()}.png"
                    )
                    put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val uri = context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                )
                uri?.let {
                    context.contentResolver.openOutputStream(it).use { outputStream ->
                        if (outputStream != null) {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        }
                    }

                    // UI 스레드에서 Toast 호출
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "이미지 다운로드 완료", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (result is ErrorResult) {
                Log.e("ImageRequest", "Error: ${result.throwable.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "이미지 다운로드 실패: ${result.throwable.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Log.e("ImageRequest", "Request failed: $result")
                // 에러 처리
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "이미지 다운로드 실패: 요청 실패", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // UI 스레드에서 Toast 호출
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "이미지 다운로드 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.d("offroad download", e.message.toString())
            }
        }
    }
}


/*
4. API 레벨 확인
WRITE_EXTERNAL_STORAGE 권한은 Android 10(Q) 이상에서 제한되어 있으므로,
API 레벨에 따라 권한 요청 방식이 달라질 수 있습니다.
Android 10 이상에서는 Scoped Storage를 사용해야 하며,
이에 따라 READ_EXTERNAL_STORAGE와 WRITE_EXTERNAL_STORAGE 대신 MediaStore를 사용해야 합니다.
 */