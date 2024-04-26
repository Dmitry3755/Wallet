package com.example.nfctagreader.ui.fragments.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.firebase.mappers.toUser
import com.example.domain.entities.User
import com.example.domain.firebase.use_case.auth.UserSignInUseCaseFb
import com.example.domain.preference.use_case.SetStringSharedPref
import com.example.domain.utils.LoadingStatus
import com.example.nfctagreader.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val userSignInUseCaseFb: UserSignInUseCaseFb,
    private val setStringSharedPref: SetStringSharedPref
) : BaseViewModel() {

    override val router = SignInRouting()

    fun trySignIn(email: String, password: String) {
        userSignInUseCaseFb.invoke(email, password).flowOn(Dispatchers.IO).onEach {
            handleLoadingStatus(it)
            if (it is LoadingStatus.Success) {
                updateProfileAndPreferenceData(it.result)
            }
        }.launchIn(viewModelScope)
    }

    private fun updateProfileAndPreferenceData(user: User) {

        setStringSharedPref(preferenceInit.USER_EMAIL_KEY, user.email!!)
        setStringSharedPref(preferenceInit.USER_PASSWORD_KEY, user.password!!)

        user.let { internalProfile.postValue(it) }
    }

    fun navigateToWalletMainFragment() {
        router.navigateToWalletMainFragment()
    }

    fun navigateToSignUpFragment() {
        router.navigateToSignUpFragment()
    }

}