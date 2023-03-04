package com.earl.authorization_feature.di

import com.earl.authorization_feature.data.mappers.UserRegisterValuesDataToDbMapper
import com.earl.authorization_feature.data.models.UserLoginValuesData
import com.earl.authorization_feature.data.models.UserRegisterValuesData
import com.earl.authorization_feature.domain.mappers.UserLoginValuesDomainToDataMapper
import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.authorization_feature.presentation.ui.login.LoginFormValidation
import com.earl.authorization_feature.presentation.ui.signIn.UserRegistrationFormValidation
import com.earl.utils.localDataSource.UsersDbDao
import com.earl.utils.localDataSource.enteties.UserLoginDb
import dagger.Module
import dagger.Provides

@Module
class AuthorizationMainModule {

    @AuthScope
    @Provides
    fun provideInteractor(
        repository: com.earl.authorization_feature.domain.Repository
    ) : com.earl.authorization_feature.domain.Interactor {
        return com.earl.authorization_feature.domain.Interactor.Base(
            repository
        )
    }

    @AuthScope
    @Provides
    fun provideRepository(
        usersDatabaseDao: UsersDbDao,
        userRegisterValuesDomainToDataMapper: UserRegisterValuesDomainToDataMapper<UserRegisterValuesData>,
        userLoginDataToDbMapper: UserRegisterValuesDataToDbMapper<UserLoginDb>,
        userLoginValuesDomainToDataMapper: UserLoginValuesDomainToDataMapper<UserLoginValuesData>
    ) : com.earl.authorization_feature.domain.Repository {
        return com.earl.authorization_feature.data.BaseRepository(
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