package com.earl.authorization_feature.data

import com.earl.authorization_feature.data.mappers.UserRegisterValuesDataToDbMapper
import com.earl.authorization_feature.data.models.UserLoginValuesData
import com.earl.authorization_feature.data.models.UserRegisterValuesData
import com.earl.authorization_feature.domain.operationResultListeners.LoginOperationResult
import com.earl.authorization_feature.domain.operationResultListeners.RegistrationOperationExceptions
import com.earl.authorization_feature.domain.operationResultListeners.RegistrationOperationResult
import com.earl.authorization_feature.domain.Repository
import com.earl.authorization_feature.domain.mappers.UserLoginValuesDomainToDataMapper
import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.authorization_feature.domain.models.UserLoginValuesDomain
import com.earl.authorization_feature.domain.models.UserRegisterValuesDomain
import com.earl.utils.localDataSource.UsersDbDao
import com.earl.utils.localDataSource.enteties.UserLoginDb
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val usersDatabaseDao: UsersDbDao,
    private val userRegisterValuesDomainToDataMapper: UserRegisterValuesDomainToDataMapper<UserRegisterValuesData>,
    private val userLoginDataToDbMapper: UserRegisterValuesDataToDbMapper<UserLoginDb>,
    private val userLoginValuesDomainToDataMapper: UserLoginValuesDomainToDataMapper<UserLoginValuesData>
) : Repository {

    override suspend fun registerNewUser(data: UserRegisterValuesDomain): RegistrationOperationResult {
        val userLoginDb = data
            .mapToData(userRegisterValuesDomainToDataMapper)
            .mapToDb(userLoginDataToDbMapper)
        val isUserAlreadyRegistered = usersDatabaseDao.checkIsUserAlreadyRegistered(
            userLoginDb.firstName,
            userLoginDb.lastName,
            userLoginDb.email
        ).isEmpty()
        return if (isUserAlreadyRegistered) {
            try {
                usersDatabaseDao.insertNewUserIntoLocalDb(userLoginDb)
                RegistrationOperationResult.Success
            } catch (e: Exception) {
                RegistrationOperationResult.Fail(e)
            }
        } else {
            RegistrationOperationResult.Fail(RegistrationOperationExceptions.USER_IS_ALREADY_REGISTERED)
        }
    }

    override suspend fun loginNewUser(data: UserLoginValuesDomain) = try {
        val loginValuesData = data.mapToData(userLoginValuesDomainToDataMapper)
        val isUserExist = usersDatabaseDao.checkIsUserAlreadyRegisteredByLoginValues(
            loginValuesData.firstName,
            loginValuesData.email
        ).isNotEmpty()
        if (isUserExist) {
            LoginOperationResult.Success
        } else {
            LoginOperationResult.Fail(Exception("???????????????????????? ?? ???????????? ?????????????? ???? ????????????"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        LoginOperationResult.Fail(e)
    }
}