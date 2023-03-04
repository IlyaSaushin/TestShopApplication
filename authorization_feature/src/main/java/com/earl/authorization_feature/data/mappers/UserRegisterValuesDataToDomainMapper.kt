package com.earl.authorization_feature.data.mappers

interface UserRegisterValuesDataToDomainMapper<T> {

    fun mapToDomain(
        firstName: String,
        password: String,
        email: String
    ) : T
}