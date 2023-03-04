package com.earl.authorization_feature.domain.mappers

interface UserRegisterValuesDomainToUiMapper<T> {

    fun mapToUi(
        firstName: String,
        password: String,
        email: String
    ) : T
}