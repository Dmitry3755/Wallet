package com.example.nfctagreader.ui.fragments.card_scan

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.camera.use_case.InitializeCamera
import com.example.domain.camera.use_case.ScanCardUseCase
import com.example.domain.camera.use_case.TakeImageProxyUseCase
import com.example.domain.entities.CardDetails
import com.example.domain.firebase.use_case.auth.GetCurrentUserUseCaseFb
import com.example.domain.utils.LoadingStatus
import com.example.nfctagreader.ui.base.BaseViewModel
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ScanCardViewModel @Inject constructor(
    private val initializeCamera: InitializeCamera,
    private val scanCardUseCase: ScanCardUseCase,
    private val getCurrentUserUseCaseFb: GetCurrentUserUseCaseFb,
    private val takeImageProxyUseCase: TakeImageProxyUseCase
) :
    BaseViewModel() {

    override val router = ScanCardRouting()

    var scanningTextMutableStateFlow: MutableLiveData<CardDetails> =
        MutableLiveData(CardDetails("", "", null))

    fun initializeCamera(
        context: Context,
        view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) {
        initializeCamera.invoke(
            context,
            view,
            lifecycleOwner,
            previewViewOriginal,
            cameraProviderFutureOriginal
        )
    }

    fun takeImageProxy() {
        takeImageProxyUseCase.invoke().flowOn(Dispatchers.IO).onEach {
            if (it is LoadingStatus.Success) {
                if (imageProxyFront.value != null) {
                    it.let { imageProxyBack.postValue(it.result) }
                } else {
                    it.let { imageProxyFront.postValue(it.result) }
                }
            }
            if(it is LoadingStatus.Failure) {
                handleLoadingStatus(it)
            }
        }.launchIn(viewModelScope)
    }

    fun scanCard() {
        scanCardUseCase.invoke(getCurrentUserUseCaseFb.invoke()!!, viewModelScope)
            .flowOn(Dispatchers.IO).onEach {
                handleLoadingStatus(it)
                if (it is LoadingStatus.Success) {
                    scanningTextMutableStateFlow.value = it.result
                }
            }.launchIn(viewModelScope)
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
}