package com.earl.authorization_feature.data.mappers

interface UserRegisterValuesDataToDbMapper<T> {

    fun map(
        firstName: String,
        password: String,
        email: String
    ) : T
}