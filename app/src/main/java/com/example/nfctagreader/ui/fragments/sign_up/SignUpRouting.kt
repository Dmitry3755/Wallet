package com.example.nfctagreader.ui.fragments.sign_up

import com.example.nfctagreader.ui.navigation.BaseRouter

class SignUpRouting : BaseRouter() {

    fun navigateToWalletMainFragment() {
        navigateToFragment(SignUpFragmentDirections.actionSignUpFragmentToWalletMainFragment())
    }

}