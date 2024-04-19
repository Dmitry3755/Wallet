package com.example.data.preference

import android.content.Context
import android.content.SharedPreferences

class PreferenceData {

    private var sharedPreference: SharedPreferences? = null
    val SHARED_PREFERENCE_NAME: String = "WALLET_APP"
    val USER_EMAIL_KEY: String = "USER_EMAIL_KEY"
    val USER_PASSWORD_KEY: String = "USER_PASSWORD_KEY"

    var userEmail : String?
        get() {
            return sharedPreference?.getString(USER_EMAIL_KEY, null)
        }
        set(value) {
            sharedPreference?.modify {
                putString(USER_EMAIL_KEY, value)
            }
        }

    var userPassword : String?
        get() {
            return sharedPreference?.getString(USER_PASSWORD_KEY, null)
        }
        set(value) {
            sharedPreference?.modify {
                putString(USER_PASSWORD_KEY, value)
            }
        }

    fun initializeSharedPreference(context: Context) {
        sharedPreference = context.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun clearData() {
        userEmail = null
        userPassword = null
    }

    private fun SharedPreferences.modify(action: SharedPreferences.Editor.() -> Unit) {
        edit().also { it.action() }.apply()
    }

}