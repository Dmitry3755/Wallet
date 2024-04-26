package com.example.nfctagreader.ui.base

import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.User
import com.example.domain.utils.LoadingStatus
import com.example.nfctagreader.utils.PreferenceInit
import com.example.nfctagreader.ui.navigation.BaseRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseViewModel : ViewModel() {

    abstract val router: BaseRouter?

    val preferenceInit: PreferenceInit = PreferenceInit()
    val internalProfile: MutableLiveData<User> = MutableLiveData()
    val profile: LiveData<User> = internalProfile
    val imageProxyFront: MutableLiveData<ImageProxy?> = MutableLiveData()
    val imageProxyBack: MutableLiveData<ImageProxy?> = MutableLiveData()

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

}