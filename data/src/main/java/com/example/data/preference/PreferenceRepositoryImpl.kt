package com.example.data.preference

import android.content.Context
import com.example.domain.preference.PreferenceRepository
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(private val preferenceData: PreferenceData) :
    PreferenceRepository {

    override fun getString(key: String): String? {
        return if (key == preferenceData.USER_EMAIL_KEY) {
            preferenceData.userEmail
        } else {
            preferenceData.userPassword
        }
    }

    override fun setString(key: String, value: String) {
        if (key == preferenceData.USER_EMAIL_KEY) {
            preferenceData.userEmail = value
        } else {
            preferenceData.userPassword = value
        }
    }

    override fun initialize(context: Context) {
        preferenceData.initializeSharedPreference(context)
    }

    override fun clearData() {
        preferenceData.clearData()
    }
}