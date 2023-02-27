package com.earl.auth_data.mappers

import com.earl.auth_domain.models.UserRegisterValuesDomain
import javax.inject.Inject

class BaseUserRegisterValuesDataToDomainMapper @Inject constructor() : UserRegisterValuesDataToDomainMapper<UserRegisterValuesDomain> {

    override fun mapToDomain(firstName: String, password: String, email: String) =
        UserRegisterValuesDomain.Base(firstName, password, email)
}