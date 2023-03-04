package com.earl.profile_feature.data

import com.earl.profile_feature.domain.LogOutOperationResultListener
import com.earl.profile_feature.domain.Repository
import com.earl.utils.localDataSource.UsersDbDao
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val usersDbDao: UsersDbDao
) : Repository {

    override suspend fun removeUserValuesFromLocalDb() = try {
        usersDbDao.removeUserDataFromLDb()
        LogOutOperationResultListener.Success
    } catch (e: Exception) {
        e.printStackTrace()
        LogOutOperationResultListener.Fail
    }
}