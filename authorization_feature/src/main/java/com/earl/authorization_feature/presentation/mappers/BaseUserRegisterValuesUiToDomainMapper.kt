package com.earl.authorization_feature.presentation.mappers

import com.earl.authorization_feature.domain.models.UserRegisterValuesDomain
import javax.inject.Inject

class BaseUserRegisterValuesUiToDomainMapper @Inject constructor():
    UserRegisterValuesUiToDomainMapper<UserRegisterValuesDomain> {

    override fun mapToDomain(firstName: String, password: String, email: String) =
        UserRegisterValuesDomain.Base(firstName, password, email)
}