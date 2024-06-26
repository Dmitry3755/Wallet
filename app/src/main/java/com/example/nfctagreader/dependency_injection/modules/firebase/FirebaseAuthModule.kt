package com.example.nfctagreader.dependency_injection.modules.firebase

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class FirebaseAuthModule {

    @Provides
    fun getFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

}