package com.earl.auth_domain

sealed class RegistrationOperationResult {
    object Success : RegistrationOperationResult()
    class Fail(val exception: Exception) : RegistrationOperationResult()
}
