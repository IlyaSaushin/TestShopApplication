package com.earl.auth_presentation.prensentation.mappers

import com.earl.auth_domain.mappers.UserRegisterValuesDomainToUiMapper
import com.earl.auth_presentation.prensentation.models.UserRegisterValuesUi
import javax.inject.Inject

class BaseUserRegisterValuesDomainToUiMapper @Inject constructor(): UserRegisterValuesDomainToUiMapper<UserRegisterValuesUi> {

    override fun mapToUi(firstName: String, password: String, email: String) =
        UserRegisterValuesUi.Base(firstName, password, email)
}