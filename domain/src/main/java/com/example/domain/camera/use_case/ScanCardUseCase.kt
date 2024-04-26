package com.example.domain.camera.use_case

import com.example.domain.camera.repositories.CameraRepository
import com.example.domain.entities.User
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ScanCardUseCase @Inject constructor(private val cameraRepository: CameraRepository) {
    operator fun invoke(user : User, viewModelScope : CoroutineScope) = cameraRepository.scanCard(user, viewModelScope)
}