package com.earl.authorization_feature.data.mappers

import com.earl.authorization_feature.data.models.UserRegisterValuesData
import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToDataMapper
import javax.inject.Inject

class BaseUserRegisterValuesDomainToDataMapper @Inject constructor() :
    UserRegisterValuesDomainToDataMapper<UserRegisterValuesData> {

    override fun mapToData(firstName: String, password: String, email: String) =
        UserRegisterValuesData.Base(firstName, password, email)
}