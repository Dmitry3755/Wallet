package com.example.nfctagreader.ui.fragments.card_scan

import com.example.nfctagreader.ui.base.BaseViewModel
import javax.inject.Inject

class ScanCardViewModel @Inject constructor() : BaseViewModel() {

    override val router =  ScanCardRouting()

}

/*
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nfctagreader.data_classes.CardDetails
import com.example.data.repositories.firebase.CardRepositoryImpl
import com.google.common.util.concurrent.ListenableFuture

class CardViewModel() : ViewModel() {

    private var cardRepositoryImpl: CardRepositoryImpl = CardRepositoryImpl()
    var scanningTextMutableStateFlow : MutableLiveData<String>  = MutableLiveData("")

    @ExperimentalGetImage
    suspend fun extractDataUseCaseViewModel(
        imageProxy: ImageProxy?,
        rotationDegrees: Int
    ) {
        scanningTextMutableStateFlow.value =
            cardRepositoryImpl.extractDataUseCase(imageProxy, rotationDegrees).value
    }

    fun extractDataFormScannerCamera(cardData: String) : CardDetails {
        return  CardDetails("", "", "", "")
         // scannerCameraRepository.extractDataFormScannerCamera(cardData)
    }

    fun checkPermissionCamera(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissionCamera(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.CAMERA),
            200
        )
    }

    fun scannerCameraInitialize(
        context: Context,
        view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) {
      */
/*  scannerCameraRepository.scannerCameraInitialize(
            context,
            view,
            lifecycleOwner,
            previewViewOriginal,
            cameraProviderFutureOriginal
        )*//*

    }

}*/
