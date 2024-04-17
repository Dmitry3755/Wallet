package com.example.data.firebase.repositories

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import com.example.data.utils.ExtractData

class CardRepositoryImplFb {

   /* fun scannerCameraInitialize(
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
    }*/

    @ExperimentalGetImage
    suspend fun extractDataUseCase(
        imageProxy: ImageProxy?,
        rotationDegrees: Int
    ): MutableLiveData<String> {
        return ExtractData.invoke(imageProxy, rotationDegrees)
    }

}