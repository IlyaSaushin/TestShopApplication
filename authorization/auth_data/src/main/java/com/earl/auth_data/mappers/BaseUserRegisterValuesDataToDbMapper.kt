package com.earl.auth_data.mappers

import com.earl.utils.localDataSource.enteties.UserLoginDb
import javax.inject.Inject

class BaseUserRegisterValuesDataToDbMapper @Inject constructor(): UserRegisterValuesDataToDbMapper<UserLoginDb> {

    override fun map(firstName: String, password: String, email: String) =
        UserLoginDb(0, firstName, password, email)
}