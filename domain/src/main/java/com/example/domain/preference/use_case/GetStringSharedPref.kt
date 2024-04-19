package com.example.domain.preference.use_case

import com.example.domain.preference.PreferenceRepository
import javax.inject.Inject

class GetStringSharedPref @Inject constructor(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(key : String) = preferenceRepository.getString(key)
}