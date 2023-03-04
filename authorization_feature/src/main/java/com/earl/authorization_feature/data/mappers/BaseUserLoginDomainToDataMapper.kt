package com.earl.authorization_feature.data.mappers

import com.earl.authorization_feature.data.models.UserLoginValuesData
import com.earl.authorization_feature.domain.mappers.UserLoginValuesDomainToDataMapper
import javax.inject.Inject

class BaseUserLoginDomainToDataMapper @Inject constructor() :
    UserLoginValuesDomainToDataMapper<UserLoginValuesData> {

    override fun map(firstName: String, email: String) =
        UserLoginValuesData(firstName, email)
}