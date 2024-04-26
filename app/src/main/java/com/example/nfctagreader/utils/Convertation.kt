package com.example.nfctagreader.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.core.graphics.rotationMatrix

@OptIn(ExperimentalGetImage::class)
fun convertBitmapFromImageProxy(
    imageProxy: ImageProxy,
    preview: PreviewView,
    screenWidth: Int,
    screenHeight: Int
): Bitmap {

    val imageBytes = imageProxy.image?.planes?.get(0)?.buffer?.let { buffer ->
        ByteArray(buffer.remaining()).apply {
            buffer.get(this)
        }
    }

    val bitmapFactory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes?.size ?: 0)
    val bitmap = Bitmap.createBitmap(
        bitmapFactory,
        0,
        0,
        bitmapFactory.width,
        bitmapFactory.height,
        rotationMatrix(90f),
        true
    )
    return scaleBitmap(bitmap, preview, screenWidth, screenHeight)

}

fun scaleBitmap(bitmap: Bitmap, preview: PreviewView, screenWidth: Int, screenHeight: Int): Bitmap {

    val finalWidthPercent = preview.width * 100 / screenWidth
    val finalHeightPercent = preview.height * 100 / screenHeight
    val finalWidth = bitmap.width * finalWidthPercent / 100
    val finalHeight = bitmap.height * finalHeightPercent / 100

    val xPercent = preview.left * 100 / screenWidth
    val yPercent = preview.top * 100 / screenHeight
    val x = bitmap.width * xPercent / 100
    val y = bitmap.height * yPercent / 100

    return Bitmap.createBitmap(
        bitmap,
        x, 0, finalWidth, bitmap.height
    )
}