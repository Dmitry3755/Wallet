package com.example.domain.camera.repositories

import android.content.Context
import android.view.View
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.domain.entities.CardDetails
import com.example.domain.entities.User
import com.example.domain.utils.LoadingStatus
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CameraRepository {

    fun initializeCamera(
        context: Context, view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    )

    fun scanCard(user: User, viewModelScope: CoroutineScope): Flow<LoadingStatus<CardDetails>>

    fun takeImageProxy(): Flow<LoadingStatus<ImageProxy>>

}