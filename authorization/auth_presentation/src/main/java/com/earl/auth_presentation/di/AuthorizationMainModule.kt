package com.earl.auth_presentation.di

import com.earl.auth_data.BaseRepository
import com.earl.auth_data.mappers.UserRegisterValuesDataToDbMapper
import com.earl.auth_data.models.UserLoginValuesData
import com.earl.auth_data.models.UserRegisterValuesData
import com.earl.auth_domain.Interactor
import com.earl.auth_domain.Repository
import com.earl.auth_domain.mappers.UserLoginValuesDomainToDataMapper
import com.earl.auth_domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.auth_presentation.prensentation.ui.login.LoginFormValidation
import com.earl.auth_presentation.prensentation.ui.signIn.UserRegistrationFormValidation
import com.earl.utils.localDataSource.UsersDbDao
import com.earl.utils.localDataSource.enteties.UserLoginDb
import com.earl.utils.remoteDataSource.NetworkService
import dagger.Module
import dagger.Provides

@Module
class AuthorizationMainModule {

    @AuthScope
    @Provides
    fun provideInteractor(
        repository: Repository
    ) : Interactor {
        return Interactor.Base(
            repository
        )
    }

    @AuthScope
    @Provides
    fun provideRepository(
        service: NetworkService,
        usersDatabaseDao: UsersDbDao,
        userRegisterValuesDomainToDataMapper: UserRegisterValuesDomainToDataMapper<UserRegisterValuesData>,
        userLoginDataToDbMapper: UserRegisterValuesDataToDbMapper<UserLoginDb>,
        userLoginValuesDomainToDataMapper: UserLoginValuesDomainToDataMapper<UserLoginValuesData>
    ) : Repository {
        return BaseRepository(
            service,
            usersDatabaseDao,
            userRegisterValuesDomainToDataMapper,
            userLoginDataToDbMapper,
            userLoginValuesDomainToDataMapper
        )
    }

    @AuthScope
    @Provides
    fun provideRegisterFormValidation() : UserRegistrationFormValidation {
        return UserRegistrationFormValidation.Base()
    }

    @AuthScope
    @Provides
    fun provideLoginFormValidation() : LoginFormValidation {
        return LoginFormValidation.Base()
    }
}