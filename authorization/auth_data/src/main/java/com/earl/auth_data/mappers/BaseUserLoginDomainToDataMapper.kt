package com.earl.auth_data.mappers

import com.earl.auth_data.models.UserLoginValuesData
import com.earl.auth_domain.mappers.UserLoginValuesDomainToDataMapper
import javax.inject.Inject

class BaseUserLoginDomainToDataMapper @Inject constructor() : UserLoginValuesDomainToDataMapper<UserLoginValuesData> {

    override fun map(firstName: String, email: String) =
        UserLoginValuesData(firstName, email)
}