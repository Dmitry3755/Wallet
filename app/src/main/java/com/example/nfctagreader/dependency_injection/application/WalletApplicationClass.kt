package com.example.nfctagreader.dependency_injection.application

import com.example.data.utils.AppResourceRepository
import com.example.domain.preference.use_case.Initialize
import com.example.nfctagreader.dependency_injection.component.AppComponent
import com.example.nfctagreader.dependency_injection.component.DaggerAppComponent
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class WalletApplicationClass : DaggerApplication() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var initialize: Initialize

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationInjector()
        AppResourceRepository.initializeResources(this)
        initialize.invoke(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}