package com.earl.auth_data.mappers

interface UserRegisterValuesDataToDomainMapper<T> {

    fun mapToDomain(
        firstName: String,
        password: String,
        email: String
    ) : T
}