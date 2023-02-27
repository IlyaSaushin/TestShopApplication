package com.earl.auth_domain

sealed class LoginOperationResult {
    object Success : LoginOperationResult()
    class Fail(val exception: Exception) : LoginOperationResult()
}
