package com.earl.authorization_feature.data.mappers

import com.earl.authorization_feature.domain.models.UserRegisterValuesDomain
import javax.inject.Inject

class BaseUserRegisterValuesDataToDomainMapper @Inject constructor() :
    UserRegisterValuesDataToDomainMapper<UserRegisterValuesDomain> {

    override fun mapToDomain(firstName: String, password: String, email: String) =
        UserRegisterValuesDomain.Base(firstName, password, email)
}