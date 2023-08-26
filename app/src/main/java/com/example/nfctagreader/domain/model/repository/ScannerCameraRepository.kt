package com.example.nfctagreader.domain.model.repository

import android.content.Context
import android.view.View
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.nfctagreader.domain.model.ExtractDataUseCase
import com.example.nfctagreader.domain.model.Extractor
import com.example.nfctagreader.domain.model.ScannerCamera
import com.example.nfctagreader.domain.model.data_class.CardDetails
import com.google.common.util.concurrent.ListenableFuture

class ScannerCameraRepository {

    fun scannerCameraInitialize(
        context: Context,
        view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) {
        ScannerCamera.cameraInitialize(
            context,
            view,
            lifecycleOwner,
            previewViewOriginal,
            cameraProviderFutureOriginal
        )
    }

    fun extractDataFormScannerCamera(cardData : String) : CardDetails {
       return Extractor.extractData(cardData)
    }

    @ExperimentalGetImage
    suspend fun extractDataUseCase(
        imageProxy: ImageProxy?,
        rotationDegrees: Int
    ): MutableLiveData<String> {
        return ExtractDataUseCase.invoke(imageProxy, rotationDegrees)
    }

}