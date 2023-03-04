package com.earl.authorization_feature.presentation.mappers

interface UserLoginValuesUiToDomainMapper<T> {

    fun map(
        firstName: String,
        email: String
    ) : T
}