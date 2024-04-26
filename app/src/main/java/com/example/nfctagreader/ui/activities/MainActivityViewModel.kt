package com.example.nfctagreader.ui.activities

import androidx.lifecycle.ViewModel
import com.example.domain.preference.use_case.GetStringSharedPref
import com.example.nfctagreader.utils.PreferenceInit
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val getStringSharedPref: GetStringSharedPref) :
    ViewModel() {

    val preferenceInit: PreferenceInit = PreferenceInit()

    fun getEmail(): String? {
        return getStringSharedPref.invoke(preferenceInit.USER_EMAIL_KEY)
    }

    fun getPassword(): String? {
        return getStringSharedPref.invoke(preferenceInit.USER_PASSWORD_KEY)
    }

}