package com.example.nfctagreader.ui.fragments.sign_up

import com.example.domain.firebase.use_case.auth.UserSignUpUseCaseFb
import com.example.nfctagreader.ui.base.BaseViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userSignUseCase: UserSignUpUseCaseFb) : BaseViewModel() {

    override val router = SignUpRouting()

    suspend fun signUp(email : String, password : String, fullName : String){
        userSignUseCase.invoke(email, password, fullName)
    }

    fun navigateToWalletMainFragment() {
        router.navigateToWalletMainFragment()
    }
}