package com.earl.auth_data.mappers

import com.earl.auth_data.models.UserRegisterValuesData
import com.earl.utils.localDataSource.mappers.UserLoginDbToDataMapper
import javax.inject.Inject

class BaseUserRegisterValuesDbToDataMapper @Inject constructor() : UserLoginDbToDataMapper<UserRegisterValuesData> {

    override fun mapToData(firstName: String, password: String, email: String) =
        UserRegisterValuesData.Base(firstName, password, email)
}