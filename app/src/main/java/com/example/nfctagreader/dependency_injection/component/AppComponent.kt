package com.example.nfctagreader.dependency_injection.component

import android.app.Application
import com.example.nfctagreader.dependency_injection.modules.firebase.FirebaseRepositoriesProvidesModule
import com.example.nfctagreader.dependency_injection.application.WalletApplicationClass
import com.example.nfctagreader.dependency_injection.modules.app.AppModule
import com.example.nfctagreader.dependency_injection.modules.app.ViewModelModule
import com.example.nfctagreader.dependency_injection.modules.camera.CameraRepositoriesProvidesModule
import com.example.nfctagreader.dependency_injection.modules.preference.PreferenceRepositoryProvidesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ViewModelModule::class,
        FirebaseRepositoriesProvidesModule::class,
        PreferenceRepositoryProvidesModule::class,
        CameraRepositoriesProvidesModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class]
)

interface AppComponent : AndroidInjector<WalletApplicationClass> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent

    }
}