package com.earl.profile_domain

sealed class LogOutOperationResultListener {
    object Success : LogOutOperationResultListener()
    object Fail : LogOutOperationResultListener()
}
