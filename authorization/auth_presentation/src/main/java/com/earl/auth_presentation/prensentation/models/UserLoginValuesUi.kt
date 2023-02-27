package com.earl.auth_presentation.prensentation.models

import com.earl.auth_presentation.prensentation.mappers.UserLoginValuesUiToDomainMapper

interface UserLoginValuesUi {

    fun <T> mapToDomain(mapper: UserLoginValuesUiToDomainMapper<T>) : T

    class Base(
        private val firstName: String,
        private val email: String
    ) : UserLoginValuesUi {

        override fun <T> mapToDomain(mapper: UserLoginValuesUiToDomainMapper<T>) =
             mapper.map(firstName, email)
    }
}