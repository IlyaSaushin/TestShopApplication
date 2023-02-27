package com.earl.auth_data

import com.earl.auth_data.mappers.UserRegisterValuesDataToDbMapper
import com.earl.auth_data.models.UserLoginValuesData
import com.earl.auth_data.models.UserRegisterValuesData
import com.earl.auth_domain.LoginOperationResult
import com.earl.auth_domain.RegistrationOperationExceptions
import com.earl.auth_domain.RegistrationOperationResult
import com.earl.auth_domain.Repository
import com.earl.auth_domain.mappers.UserLoginValuesDomainToDataMapper
import com.earl.auth_domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.auth_domain.models.UserLoginValuesDomain
import com.earl.auth_domain.models.UserRegisterValuesDomain
import com.earl.utils.localDataSource.UsersDbDao
import com.earl.utils.localDataSource.enteties.UserLoginDb
import com.earl.utils.remoteDataSource.NetworkService
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val networkService: NetworkService,
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
            LoginOperationResult.Fail(Exception("Пользователь с такими данными не найден"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        LoginOperationResult.Fail(e)
    }
}