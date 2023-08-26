package com.example.nfctagreader.domain.model

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


object ExtractDataUseCase {

    var scanningTextMutableStateFlow = MutableLiveData("")
    val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    @ExperimentalGetImage
    suspend operator fun invoke(
        imageProxy: ImageProxy?,
        rotationDegrees: Int
    ): MutableLiveData<String> {
        val mediaImage = imageProxy?.image
        val image =
            InputImage.fromMediaImage(mediaImage!!, rotationDegrees)

        coroutineScope {
            textRecognizer.process(image).addOnSuccessListener { text ->
                scanningTextMutableStateFlow.value = text.text
            }
            delay(2000)
        }
        return scanningTextMutableStateFlow
    }
}
