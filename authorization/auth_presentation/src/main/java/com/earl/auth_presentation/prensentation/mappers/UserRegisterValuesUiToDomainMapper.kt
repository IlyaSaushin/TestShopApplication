package com.earl.auth_presentation.prensentation.mappers

interface UserRegisterValuesUiToDomainMapper<T> {

    fun mapToDomain(
        firstName: String,
        password: String,
        email: String
    ) : T
}