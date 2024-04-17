package com.example.nfctagreader.ui.fragments.sign_in

import com.example.nfctagreader.ui.navigation.BaseRouter

class SignInRouting : BaseRouter() {

    fun navigateToWalletMainFragment() {
        navigateToFragment(SignInFragmentDirections.actionSignInFragmentToWalletMainFragment())
    }

    fun navigateToSignUpFragment(){
        navigateToFragment(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

}