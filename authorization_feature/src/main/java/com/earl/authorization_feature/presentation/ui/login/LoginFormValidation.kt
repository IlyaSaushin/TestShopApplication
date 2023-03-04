package com.earl.authorization_feature.presentation.ui.login

import android.widget.EditText
import com.earl.authorization_feature.R
import com.earl.utils.coreUi.isEmailValid
import javax.inject.Inject

interface LoginFormValidation {

    fun validate(
        firstName: EditText,
        email: EditText
    ) : Boolean

    class Base @Inject constructor() : LoginFormValidation {
        override fun validate(firstName: EditText, email: EditText): Boolean {
            var validation = true
            val context = firstName.context
            if (firstName.text.toString().trim().isEmpty()) {
                firstName.error = context.getString(R.string.empty_string_err)
                validation = false
            } else if (email.text.toString().trim().isEmpty()) {
                email.error = context.getString(R.string.empty_string_err)
                validation = false
            } else if (!email.text.toString().isEmailValid()) {
                email.error = context.getString(R.string.shor_pass_err)
                validation = false
            }
            return validation
        }
    }
}