package com.earl.auth_presentation.prensentation.mappers

import com.earl.auth_domain.models.UserRegisterValuesDomain
import javax.inject.Inject

class BaseUserRegisterValuesUiToDomainMapper @Inject constructor():
    UserRegisterValuesUiToDomainMapper<UserRegisterValuesDomain> {

    override fun mapToDomain(firstName: String, password: String, email: String) =
        UserRegisterValuesDomain.Base(firstName, password, email)
}