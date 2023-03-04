package com.earl.authorization_feature.domain.mappers

interface UserRegisterValuesDomainToDataMapper<T> {

    fun mapToData(
        firstName: String,
        password: String,
        email: String
    ) : T
}