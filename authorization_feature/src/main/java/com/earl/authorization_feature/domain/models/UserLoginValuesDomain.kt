package com.earl.authorization_feature.domain.models

import com.earl.authorization_feature.domain.mappers.UserLoginValuesDomainToDataMapper

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