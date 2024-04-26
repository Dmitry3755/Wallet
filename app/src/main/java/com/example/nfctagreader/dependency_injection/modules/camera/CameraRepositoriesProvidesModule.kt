package com.example.nfctagreader.dependency_injection.modules.camera

import com.example.data.camera.repositories.CameraRepositoryImplFb
import com.example.domain.camera.repositories.CameraRepository
import com.example.nfctagreader.dependency_injection.annotations.qualifiers.CameraQualifier
import dagger.Module
import dagger.Provides

@CameraQualifier
@Module(includes = [CameraModule::class])
class CameraRepositoriesProvidesModule {

    @Provides
    fun bindsCameraRepository(cameraRepositoryImplFb: CameraRepositoryImplFb): CameraRepository =
        cameraRepositoryImplFb

}