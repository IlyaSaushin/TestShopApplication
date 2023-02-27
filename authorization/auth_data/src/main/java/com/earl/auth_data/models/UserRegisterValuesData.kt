package com.earl.auth_data.models

import com.earl.auth_data.mappers.UserRegisterValuesDataToDbMapper
import com.earl.auth_data.mappers.UserRegisterValuesDataToDomainMapper

interface UserRegisterValuesData {

    fun <T> mapToDomain(mapper: UserRegisterValuesDataToDomainMapper<T>) : T

    fun <T> mapToDb(mapper: UserRegisterValuesDataToDbMapper<T>) : T

    class Base(
        private val firstName: String,
        private val password: String,
        private val email: String
    ) : UserRegisterValuesData {

        override fun <T> mapToDomain(mapper: UserRegisterValuesDataToDomainMapper<T>) =
            mapper.mapToDomain(firstName, password, email)

        override fun <T> mapToDb(mapper: UserRegisterValuesDataToDbMapper<T>) =
            mapper.map(firstName, password, email)
    }
}