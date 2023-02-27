package com.earl.auth_data.mappers

import com.earl.auth_data.models.UserRegisterValuesData
import com.earl.auth_domain.mappers.UserRegisterValuesDomainToDataMapper
import javax.inject.Inject

class BaseUserRegisterValuesDomainToDataMapper @Inject constructor() : UserRegisterValuesDomainToDataMapper<UserRegisterValuesData> {

    override fun mapToData(firstName: String, password: String, email: String) =
        UserRegisterValuesData.Base(firstName, password, email)
}