package com.earl.auth_presentation.prensentation.mappers

interface UserLoginValuesUiToDomainMapper<T> {

    fun map(
        firstName: String,
        email: String
    ) : T
}