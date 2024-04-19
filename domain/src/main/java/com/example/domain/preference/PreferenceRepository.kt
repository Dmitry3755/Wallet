package com.example.domain.preference

import android.content.Context

interface PreferenceRepository {

    fun initialize(context: Context)

    fun getString(key: String): String?

    fun setString(key: String, value: String)

    fun clearData()

}