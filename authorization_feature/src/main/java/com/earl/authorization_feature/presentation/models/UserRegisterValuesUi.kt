package com.earl.authorization_feature.presentation.models

import com.earl.authorization_feature.presentation.mappers.UserRegisterValuesUiToDomainMapper

interface UserRegisterValuesUi {

    fun <T> mapToDomain(mapper: UserRegisterValuesUiToDomainMapper<T>) : T

    class Base(
        private val firstName: String,
        private val password: String,
        private val email: String
    ) : UserRegisterValuesUi {

        override fun <T> mapToDomain(mapper: UserRegisterValuesUiToDomainMapper<T>) =
            mapper.mapToDomain(firstName, password, email)
    }
}