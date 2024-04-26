package com.example.domain.camera.use_case

import android.content.Context
import android.view.View
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.domain.camera.repositories.CameraRepository
import com.google.common.util.concurrent.ListenableFuture
import javax.inject.Inject

class InitializeCamera @Inject constructor(private val cameraRepository: CameraRepository) {
    operator fun invoke(
        context: Context, view: View?, lifecycleOwner: LifecycleOwner, previewView: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) =
        cameraRepository.initializeCamera(
            context,
            view,
            lifecycleOwner,
            previewView,
            cameraProviderFutureOriginal
        )
}