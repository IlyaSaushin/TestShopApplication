package com.earl.authorization_feature.domain.models

import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToUiMapper

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