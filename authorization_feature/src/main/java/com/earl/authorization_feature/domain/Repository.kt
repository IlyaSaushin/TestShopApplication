package com.earl.authorization_feature.domain

import com.earl.authorization_feature.domain.models.UserLoginValuesDomain
import com.earl.authorization_feature.domain.models.UserRegisterValuesDomain
import com.earl.authorization_feature.domain.operationResultListeners.LoginOperationResult
import com.earl.authorization_feature.domain.operationResultListeners.RegistrationOperationResult

interface Repository {

    suspend fun registerNewUser(data: UserRegisterValuesDomain) : RegistrationOperationResult

    suspend fun loginNewUser(data: UserLoginValuesDomain) : LoginOperationResult
}