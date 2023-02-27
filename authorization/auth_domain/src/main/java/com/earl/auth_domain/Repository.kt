package com.earl.auth_domain

import com.earl.auth_domain.models.UserLoginValuesDomain
import com.earl.auth_domain.models.UserRegisterValuesDomain

interface Repository {

    suspend fun registerNewUser(data: UserRegisterValuesDomain) : RegistrationOperationResult

    suspend fun loginNewUser(data: UserLoginValuesDomain) : LoginOperationResult
}