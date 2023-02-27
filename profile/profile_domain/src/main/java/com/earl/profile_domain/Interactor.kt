package com.earl.profile_domain

import javax.inject.Inject

interface Interactor {

    suspend fun removeUserDataFromLocalDb() : LogOutOperationResultListener

    class Base @Inject constructor(
        private val repository: Repository
    ) : Interactor {

        override suspend fun removeUserDataFromLocalDb() =
            repository.removeUserValuesFromLocalDb()
    }
}