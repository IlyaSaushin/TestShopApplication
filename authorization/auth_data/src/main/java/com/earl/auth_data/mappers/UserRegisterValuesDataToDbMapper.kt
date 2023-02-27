package com.earl.auth_data.mappers

interface UserRegisterValuesDataToDbMapper<T> {

    fun map(
        firstName: String,
        password: String,
        email: String
    ) : T
}