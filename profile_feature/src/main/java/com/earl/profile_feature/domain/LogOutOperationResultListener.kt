package com.earl.profile_feature.domain

sealed class LogOutOperationResultListener {
    object Success : LogOutOperationResultListener()
    object Fail : LogOutOperationResultListener()
}
