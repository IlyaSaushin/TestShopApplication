package com.earl.auth_domain.models

import com.earl.auth_domain.mappers.UserLoginValuesDomainToDataMapper

interface UserLoginValuesDomain {

    fun <T> mapToData(mapper: UserLoginValuesDomainToDataMapper<T>) : T

    class Base(
        private val firstName: String,
        private val email: String
    ) : UserLoginValuesDomain {

        override fun <T> mapToData(mapper: UserLoginValuesDomainToDataMapper<T>) =
            mapper.map(firstName, email)
    }
}