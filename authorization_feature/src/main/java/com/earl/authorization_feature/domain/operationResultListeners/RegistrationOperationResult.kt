package com.earl.authorization_feature.domain.operationResultListeners

sealed class RegistrationOperationResult {
    object Success : RegistrationOperationResult()
    class Fail(val exception: Exception) : RegistrationOperationResult()
}
