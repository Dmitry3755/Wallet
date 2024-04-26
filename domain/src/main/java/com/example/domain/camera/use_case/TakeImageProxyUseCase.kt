package com.example.domain.camera.use_case

import com.example.domain.camera.repositories.CameraRepository
import javax.inject.Inject

class TakeImageProxyUseCase @Inject constructor(private val cameraRepository: CameraRepository) {
    operator fun invoke() = cameraRepository.takeImageProxy()
}