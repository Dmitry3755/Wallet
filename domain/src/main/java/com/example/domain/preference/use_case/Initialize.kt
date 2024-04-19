package com.example.domain.preference.use_case

import android.content.Context
import com.example.domain.preference.PreferenceRepository
import javax.inject.Inject

class Initialize @Inject constructor(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(context: Context) = preferenceRepository.initialize(context)
}