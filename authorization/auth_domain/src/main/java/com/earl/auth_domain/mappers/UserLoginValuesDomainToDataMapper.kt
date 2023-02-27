package com.earl.auth_domain.mappers

interface UserLoginValuesDomainToDataMapper<T> {

    fun map(
        firstName: String,
        email: String
    ) : T
}