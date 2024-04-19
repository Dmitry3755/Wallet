package com.example.nfctagreader.utils

import android.widget.CheckBox
import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun isEnteringTextValid(editText: TextInputEditText, message: String): Boolean {
    return if (editText.text.isNullOrEmpty()) {
        showOrHideError(editText, message)
        true
    } else {
        showOrHideError(editText, null)
        false
    }
}

fun isUserCredentialsEmail(editText: TextInputEditText, message: String): Boolean {
    return if (!PatternsCompat.EMAIL_ADDRESS.matcher(editText.text).matches()) {
        showOrHideError(editText, message)
        true
    } else {
        showOrHideError(editText, null)
        false
    }
}

fun isUserCredentialsPassword(editText: TextInputEditText, message: String): Boolean {
    return if (editText.text?.length!! < 6) {
        showOrHideError(editText, message)
        true
    } else {
        showOrHideError(editText, null)
        false
    }
}

fun isUserCheckUserAgreement(check: CheckBox, layout: TextInputLayout, message: String): Boolean {
    return if (!check.isChecked) {
        showRequiredCheckBox(layout, message)
        true
    } else {
        showRequiredCheckBox(layout, null)
        false
    }
}

fun showRequiredCheckBox(layout: TextInputLayout, message: String?) {
    layout.isErrorEnabled = true
    layout.helperText = message
}

fun showOrHideError(editText: TextInputEditText, message: String?) {
    editText.error = message
}
