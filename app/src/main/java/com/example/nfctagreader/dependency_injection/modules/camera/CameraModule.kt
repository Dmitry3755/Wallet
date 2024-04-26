package com.example.nfctagreader.dependency_injection.modules.camera

import android.content.Context
import android.view.View
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.data.camera.entities.CameraEntities
import com.example.nfctagreader.dependency_injection.annotations.FragmentScope
import com.google.common.util.concurrent.ListenableFuture
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CameraModule {

    @Provides
    @Singleton
    fun provideCamera(): CameraEntities {
        return CameraEntities()
    }
}