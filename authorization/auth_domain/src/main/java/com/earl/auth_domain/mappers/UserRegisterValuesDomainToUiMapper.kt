package com.earl.auth_domain.mappers

interface UserRegisterValuesDomainToUiMapper<T> {

    fun mapToUi(
        firstName: String,
        password: String,
        email: String
    ) : T
}