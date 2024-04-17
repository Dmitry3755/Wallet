package com.example.nfctagreader.ui.fragments.sign_in

import com.example.nfctagreader.ui.base.BaseViewModel
import javax.inject.Inject

class SignInViewModel @Inject constructor() : BaseViewModel() {

    override val router = SignInRouting()

    fun navigateToWalletMainFragment() {
        router.navigateToWalletMainFragment()
    }

    fun navigateToSignUpFragment() {
        router.navigateToSignUpFragment()
    }

}