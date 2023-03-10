package com.earl.authorization_feature.domain.operationResultListeners

sealed class LoginOperationResult {
    object Success : LoginOperationResult()
    class Fail(val exception: Exception) : LoginOperationResult()
}
