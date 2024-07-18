package com.teamoffroad.feature.explore.presentation.util

import android.os.Handler
import android.os.Looper
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(
    private val onBarcodeDetected: (String) -> Unit,
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)
    private val handler = Handler(Looper.getMainLooper())
    private var isAnalyzing = false

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        if (isAnalyzing) {
            imageProxy.close()
            return
        }

        isAnalyzing = true
        imageProxy.image?.let { image ->
            scanner.process(
                InputImage.fromMediaImage(
                    image, imageProxy.imageInfo.rotationDegrees
                )
            ).addOnSuccessListener { barcodes ->
                barcodes?.takeIf { it.isNotEmpty() }
                    ?.mapNotNull { it.rawValue }
                    ?.joinToString(",")
                    ?.let { onBarcodeDetected(it) }
            }.addOnCompleteListener {
                imageProxy.close()
                handler.postDelayed({
                    isAnalyzing = false
                }, 1200)
            }
        }
    }
}
