package com.earl.profile_feature.domain

interface Repository {

    suspend fun removeUserValuesFromLocalDb() : LogOutOperationResultListener
}