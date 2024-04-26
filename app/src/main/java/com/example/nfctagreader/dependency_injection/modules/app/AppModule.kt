package com.example.nfctagreader.dependency_injection.modules.app

import android.app.Application
import android.content.Context
import com.example.nfctagreader.dependency_injection.annotations.ActivityScope
import com.example.nfctagreader.ui.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppModule {

    @Binds
    fun bindContext(context: Application): Context

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentsModule::class])
    fun providesMainActivity(): MainActivity
}