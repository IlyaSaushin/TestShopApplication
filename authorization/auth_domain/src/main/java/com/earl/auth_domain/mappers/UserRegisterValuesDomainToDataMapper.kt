package com.earl.auth_domain.mappers

interface UserRegisterValuesDomainToDataMapper<T> {

    fun mapToData(
        firstName: String,
        password: String,
        email: String
    ) : T
}