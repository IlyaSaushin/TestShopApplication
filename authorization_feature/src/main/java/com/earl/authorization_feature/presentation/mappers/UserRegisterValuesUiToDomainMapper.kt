package com.earl.authorization_feature.presentation.mappers

interface UserRegisterValuesUiToDomainMapper<T> {

    fun mapToDomain(
        firstName: String,
        password: String,
        email: String
    ) : T
}