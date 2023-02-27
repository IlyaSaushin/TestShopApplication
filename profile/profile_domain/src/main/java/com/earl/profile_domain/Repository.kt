package com.earl.profile_domain

interface Repository {

    suspend fun removeUserValuesFromLocalDb() : LogOutOperationResultListener
}