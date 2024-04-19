package com.example.nfctagreader.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.preference.PreferenceData
import com.example.domain.entities.User
import com.example.domain.preference.use_case.GetStringSharedPref
import com.example.domain.preference.use_case.SetStringSharedPref
import com.example.domain.utils.LoadingStatus
import com.example.nfctagreader.data_classes.PreferenceInit
import com.example.nfctagreader.ui.navigation.BaseRouter
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    abstract val router: BaseRouter?

    val preferenceInit: PreferenceInit = PreferenceInit()
    val internalProfile: MutableLiveData<User> = MutableLiveData()
    val profile: LiveData<User> = internalProfile

    val showSnackBarWithMessage = MutableSharedFlow<String>(extraBufferCapacity = 1)

    fun <T> handleLoadingStatus(status: LoadingStatus<T>) {
        when (status) {
            is LoadingStatus.Success -> {}

            is LoadingStatus.Loading -> {}

            is LoadingStatus.Failure -> {
                showSnackBarWithMessage.tryEmit(status.exception)
            }
        }
    }

    /*        setStringSharedPref(preferenceInit.USER_EMAIL_KEY, user.email!!)
            setStringSharedPref(preferenceInit.USER_PASSWORD_KEY, user.password!!)

            user.let { internalProfile.postValue(it) }*/

}