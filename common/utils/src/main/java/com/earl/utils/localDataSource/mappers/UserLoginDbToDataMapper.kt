package com.earl.utils.localDataSource.mappers

interface UserLoginDbToDataMapper<T> {

    fun mapToData(
        firstName: String,
        password: String,
        email: String
    ) : T
}