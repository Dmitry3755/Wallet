package com.example.data.camera.entities

import android.content.Context
import android.view.View
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCaseGroup
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.Executors

class CameraEntities {

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var previewView: PreviewView
    private lateinit var preview: Preview
    private lateinit var cameraSelector: CameraSelector
    private lateinit var imageAnalysis: ImageAnalysis
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var useCaseGroup: UseCaseGroup
    lateinit var imageCapture: ImageCapture
    lateinit var camera: Camera

    fun initialize(
        context: Context,
        view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) {
        previewView = previewViewOriginal
        cameraProviderFuture = cameraProviderFutureOriginal
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            cameraBuilder(lifecycleOwner)
            cameraProvider.unbindAll()

            cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, useCaseGroup
            )
            cameraProvider.bindToLifecycle(
                lifecycleOwner, cameraSelector, preview, imageCapture, imageAnalysis
            )
        }, ContextCompat.getMainExecutor(context))
    }

    private fun cameraBuilder(lifecycleOwner: LifecycleOwner) {
        preview = previewBuilder()
        cameraSelector = cameraSelectorBuilder()
        imageAnalysis = imageAnalysisBuilder()
        imageCapture = imageCaptureBuilder()
        imageAnalysis.setAnalyzer(
            Executors.newSingleThreadExecutor()
        ) { imageProxy ->
            imageProxy.imageInfo.rotationDegrees
            imageProxy.close()
        }

        useCaseGroup = useCaseGroupBuilder()
        preview.setSurfaceProvider(previewView.getSurfaceProvider())

        camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
    }

    private fun useCaseGroupBuilder(): UseCaseGroup {
        return UseCaseGroup.Builder()
            .addUseCase(preview)
            .addUseCase(imageAnalysis)
            .addUseCase(imageCapture)
            .setViewPort(previewView.viewPort!!)
            .build()
    }

    private fun imageAnalysisBuilder(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    private fun previewBuilder(): Preview {
        return Preview.Builder().build()
    }

    private fun cameraSelectorBuilder(): CameraSelector {
        return CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
    }

    private fun imageCaptureBuilder(): ImageCapture {
        return ImageCapture.Builder().build()
    }
}
