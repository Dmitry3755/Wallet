package com.example.nfctagreader.dependency_injection.component

import android.app.Application
import android.content.Context
import com.example.nfctagreader.dependency_injection.annotations.ApplicationContext
import com.example.nfctagreader.dependency_injection.modules.FirebaseRepositoriesBindsModule
import com.example.nfctagreader.dependency_injection.application.WalletApplicationClass
import com.example.nfctagreader.dependency_injection.modules.AppModule
import com.example.nfctagreader.dependency_injection.modules.PreferenceModule
import com.example.nfctagreader.dependency_injection.modules.ViewModelModule
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
        FirebaseRepositoriesBindsModule::class,
        PreferenceModule::class,
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