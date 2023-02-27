package com.earl.auth_domain.models

import com.earl.auth_domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.auth_domain.mappers.UserRegisterValuesDomainToUiMapper

interface UserRegisterValuesDomain {

    fun <T> mapToUi(mapper: UserRegisterValuesDomainToUiMapper<T>) : T

    fun <T> mapToData(mapper: UserRegisterValuesDomainToDataMapper<T>) : T

    class Base(
        private val firstName: String,
        private val password: String,
        private val email: String
    ) : UserRegisterValuesDomain {

        override fun <T> mapToUi(mapper: UserRegisterValuesDomainToUiMapper<T>) =
            mapper.mapToUi(firstName, password, email)

        override fun <T> mapToData(mapper: UserRegisterValuesDomainToDataMapper<T>) =
            mapper.mapToData(firstName, password, email)
    }
}