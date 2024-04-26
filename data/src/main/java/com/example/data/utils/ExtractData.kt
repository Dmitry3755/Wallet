package com.example.data.utils

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import com.example.domain.utils.LoadingStatus
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


object ExtractData {

    @ExperimentalGetImage
    operator fun invoke(
        imageProxy: ImageProxy,
        rotationDegrees: Int
    ): Flow<LoadingStatus<String>> = callbackFlow {
        trySend(LoadingStatus.Loading())
        val result = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(
            InputImage.fromMediaImage(
                imageProxy.image!!,
                rotationDegrees
            )
        )
        result.addOnSuccessListener {
            trySend(LoadingStatus.Success(it.text))
            close()
        }
        result.addOnFailureListener {
            trySend(LoadingStatus.Failure(it.message.toString()))
            close()
        }
        awaitClose { result.result }
    }
}
