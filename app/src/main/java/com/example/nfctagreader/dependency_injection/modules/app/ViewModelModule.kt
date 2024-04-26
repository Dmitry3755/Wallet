package com.example.nfctagreader.dependency_injection.modules.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nfctagreader.dependency_injection.annotations.ViewModelKey
import com.example.nfctagreader.ui.activities.MainActivityViewModel
import com.example.nfctagreader.ui.fragments.card_scan.ScanCardViewModel
import com.example.nfctagreader.ui.fragments.sign_in.SignInViewModel
import com.example.nfctagreader.ui.fragments.sign_up.SignUpViewModel
import com.example.nfctagreader.ui.fragments.wallet.WalletViewModel
import com.example.nfctagreader.view_model.DaggerViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WalletViewModel::class)
    abstract fun bindWalletViewModel(walletViewModel: WalletViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanCardViewModel::class)
    abstract fun bindScanCardViewModel(scanCardViewModel: ScanCardViewModel): ViewModel
}