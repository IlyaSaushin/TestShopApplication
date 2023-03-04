package com.earl.authorization_feature.domain.mappers

interface UserLoginValuesDomainToDataMapper<T> {

    fun map(
        firstName: String,
        email: String
    ) : T
}