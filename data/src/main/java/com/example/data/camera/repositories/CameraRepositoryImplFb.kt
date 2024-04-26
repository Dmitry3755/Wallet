package com.example.data.camera.repositories

import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.data.R
import com.example.data.camera.entities.CameraEntities
import com.example.data.camera.extension.takePicture
import com.example.data.utils.AppResourceRepository
import com.example.data.utils.ExtractData
import com.example.data.utils.parserData
import com.example.domain.camera.repositories.CameraRepository
import com.example.domain.entities.CardDetails
import com.example.domain.entities.User
import com.example.domain.utils.LoadingStatus
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import javax.inject.Inject

class CameraRepositoryImplFb @Inject constructor(private val camera: CameraEntities) :
    CameraRepository {

    override fun initializeCamera(
        context: Context,
        view: View?,
        lifecycleOwner: LifecycleOwner,
        previewViewOriginal: PreviewView,
        cameraProviderFutureOriginal: ListenableFuture<ProcessCameraProvider>
    ) {
        camera.initialize(
            context,
            view,
            lifecycleOwner,
            previewViewOriginal, cameraProviderFutureOriginal
        )
    }

    override fun takeImageProxy(): Flow<LoadingStatus<ImageProxy>> = flow {
        emit(LoadingStatus.Loading())
        try {
            val imageProxy = camera.imageCapture.takePicture(Executors.newSingleThreadExecutor())
            emit(LoadingStatus.Success(imageProxy))
        } catch (e: Exception) {
            emit(
                LoadingStatus.Failure(
                    e.message
                        ?: AppResourceRepository.getString(R.string.error_something_went_wrong)
                )
            )
        }
    }

    @OptIn(ExperimentalGetImage::class)
    override fun scanCard(
        user: User,
        viewModelScope: CoroutineScope
    ): Flow<LoadingStatus<CardDetails>> = flow {
        emit(LoadingStatus.Loading())
        val imageProxy = camera.imageCapture.takePicture(Executors.newSingleThreadExecutor())
        ExtractData.invoke(
            imageProxy,
            imageProxy.imageInfo.rotationDegrees
        ).onEach {
            if (it is LoadingStatus.Success) {
                emit(
                    LoadingStatus.Success(
                        parserData(
                            it.result, user
                        )
                    )
                )
            }
        }.catch {
            emit(
                LoadingStatus.Failure(
                    it.message
                        ?: AppResourceRepository.getString(R.string.error_something_went_wrong)
                )
            )
        }.collect()
    }
}