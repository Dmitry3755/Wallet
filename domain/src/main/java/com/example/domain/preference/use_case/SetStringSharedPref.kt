package com.example.domain.preference.use_case

import com.example.domain.preference.PreferenceRepository
import javax.inject.Inject

class SetStringSharedPref @Inject constructor(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(key: String, value: String) = preferenceRepository.setString(key, value)
}