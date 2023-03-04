package com.earl.authorization_feature.presentation.ui.signIn

import android.widget.EditText
import com.earl.authorization_feature.R
import com.earl.utils.coreUi.isEmailValid
import javax.inject.Inject

interface UserRegistrationFormValidation {

    fun validate(firstName: EditText, lastName: EditText, email: EditText): Boolean

    class Base @Inject constructor() : UserRegistrationFormValidation {
        override fun validate(firstName: EditText, lastName: EditText, email: EditText): Boolean {
            var validation = true
            val context = firstName.context
            if (firstName.text.toString().trim { it <= ' ' }.isEmpty()) {
                firstName.error = context.getString(R.string.empty_string_err)
                validation = false
            } else if (lastName.text.toString().trim { it <= ' ' }.isEmpty()) {
                lastName.error = context.getString(R.string.empty_string_err)
                validation = false
            } else if (!email.text.toString().isEmailValid()) {
                email.error = context.getString(R.string.shor_pass_err)
                validation = false
            }
            return validation
        }
    }
}