package com.example.nfctagreader.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.User
import com.example.nfctagreader.ui.navigation.BaseRouter

abstract class BaseViewModel : ViewModel() {
    abstract val router : BaseRouter?

    private val internalUser: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = internalUser

}