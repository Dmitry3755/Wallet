package com.example.nfctagreader.ui.fragments.sign_up

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.User
import com.example.domain.firebase.use_case.auth.UserSignUpUseCaseFb
import com.example.domain.preference.use_case.SetStringSharedPref
import com.example.domain.utils.LoadingStatus
import com.example.nfctagreader.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val userSignUseCase: UserSignUpUseCaseFb,
    private val setStringSharedPref: SetStringSharedPref
) :
    BaseViewModel() {

    private val internalIsProfileUpdated: MutableLiveData<Boolean> = MutableLiveData()

    override val router = SignUpRouting()

    fun trySignUp(email: String, password: String, fullName: String) {
        userSignUseCase.invoke(email, password, fullName).flowOn(Dispatchers.IO).onEach {
            handleLoadingStatus(it)
            if (it is LoadingStatus.Success) {
                internalIsProfileUpdated.value = true
                updateProfileAndPreferenceData(it.result)
            }
            if (it is LoadingStatus.Failure) {
                internalIsProfileUpdated.value = false
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
}