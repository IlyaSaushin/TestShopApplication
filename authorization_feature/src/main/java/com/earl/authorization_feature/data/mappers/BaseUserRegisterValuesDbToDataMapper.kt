package com.earl.authorization_feature.data.mappers

import com.earl.authorization_feature.data.models.UserRegisterValuesData
import com.earl.utils.localDataSource.mappers.UserLoginDbToDataMapper
import javax.inject.Inject

class BaseUserRegisterValuesDbToDataMapper @Inject constructor() : UserLoginDbToDataMapper<UserRegisterValuesData> {

    override fun mapToData(firstName: String, password: String, email: String) =
        UserRegisterValuesData.Base(firstName, password, email)
}