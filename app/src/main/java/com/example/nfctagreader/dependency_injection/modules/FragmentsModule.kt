package com.example.nfctagreader.dependency_injection.modules

import com.example.nfctagreader.dependency_injection.annotations.FragmentScope
import com.example.nfctagreader.ui.fragments.card_scan.ScanCardFragment
import com.example.nfctagreader.ui.fragments.sign_in.SignInFragment
import com.example.nfctagreader.ui.fragments.sign_up.SignUpFragment
import com.example.nfctagreader.ui.fragments.wallet.WalletMainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun provideWalletMainFragment() : WalletMainFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun provideSignUpFragment() : SignUpFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun provideSignInFragment() : SignInFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun provideScanCardFragment() : ScanCardFragment

}