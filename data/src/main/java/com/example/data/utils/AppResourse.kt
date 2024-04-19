package com.example.data.utils

import android.content.Context
import androidx.annotation.StringRes

object AppResourceRepository {

    private var applicationContext: Context? = null

    fun initializeResources(context: Context) {
        applicationContext = context
    }

    fun getString(@StringRes id: Int) = applicationContext?.getString(id) ?: ""

}