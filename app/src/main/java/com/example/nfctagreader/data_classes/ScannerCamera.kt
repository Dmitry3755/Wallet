/* package com.example.nfctagreader.data_classes

import android.content.Context
import android.view.View
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import java.util.concurrent.Executors

data class varScannerCamera (
    var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    var previewView: PreviewView,
    var preview: Preview,
    var cameraSelector: CameraSelector,
    var imageCapture: ImageCapture,
    var imageAnalysis: ImageAnalysis,
    var metadata: FirebaseVisionImageMetadata,
    var camera: Camera
) {
    fun cameraInitialize(
        context: Context,
        view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) {
        previewView = previewViewOriginal
        cameraProviderFuture = cameraProviderFutureOriginal
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider, view, lifecycleOwner)
        }, ContextCompat.getMainExecutor(context))
    }

    private fun bindPreview(
        cameraProvider: ProcessCameraProvider,
        view: View?,
        lifecycleOwner: LifecycleOwner
    ) {
        preview = Preview.Builder()
            .build()

        cameraSelector = CameraSelector.Builder()requireLensFacing
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        imageCapture = ImageCapture.Builder()
            .setTargetRotation(view?.display!!.rotation)
            .build()

        imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(
            Executors.newSingleThreadExecutor(),
            ImageAnalysis.Analyzer { imageProxy ->
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                imageProxy.close()
            })

         metadata = FirebaseVisionImageMetadata.Builder()
            .setWidth(1280)
            .setHeight(760)
            .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
            .setRotation(0)
            .build()

        preview.setSurfaceProvider(previewView.getSurfaceProvider())

        camera = cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            imageCapture,
            imageAnalysis,
            preview
        )
        camera.cameraControl.setZoomRatio(2.0f)
        camera.cameraControl.enableTorch(false)
    }
} */
