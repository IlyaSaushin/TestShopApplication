package com.earl.auth_domain

import com.earl.auth_domain.models.UserLoginValuesDomain
import com.earl.auth_domain.models.UserRegisterValuesDomain
import javax.inject.Inject

interface Interactor {

    suspend fun insertNewUserIntoLocalDb(userData: UserRegisterValuesDomain) : RegistrationOperationResult

    suspend fun loginUser(userData: UserLoginValuesDomain) : LoginOperationResult

    class Base @Inject constructor(
        private val repository: Repository
    ) : Interactor {

        override suspend fun insertNewUserIntoLocalDb(userData: UserRegisterValuesDomain) =
            repository.registerNewUser(userData)

        override suspend fun loginUser(userData: UserLoginValuesDomain) =
            repository.loginNewUser(userData)
    }
}