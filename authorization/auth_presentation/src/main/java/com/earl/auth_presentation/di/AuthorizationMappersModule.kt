package com.earl.auth_presentation.di

import com.earl.auth_data.mappers.*
import com.earl.auth_data.models.UserLoginValuesData
import com.earl.auth_data.models.UserRegisterValuesData
import com.earl.auth_domain.mappers.UserLoginValuesDomainToDataMapper
import com.earl.auth_domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.auth_domain.mappers.UserRegisterValuesDomainToUiMapper
import com.earl.auth_domain.models.UserLoginValuesDomain
import com.earl.auth_domain.models.UserRegisterValuesDomain
import com.earl.auth_presentation.prensentation.mappers.*
import com.earl.auth_presentation.prensentation.models.UserRegisterValuesUi
import com.earl.utils.localDataSource.enteties.UserLoginDb
import com.earl.utils.localDataSource.mappers.UserLoginDbToDataMapper
import dagger.Module
import dagger.Provides

@Module
class AuthorizationMappersModule {

    @AuthScope
    @Provides
    fun provideUserLoginDataToDomainMapper() : UserRegisterValuesDataToDomainMapper<UserRegisterValuesDomain> {
        return BaseUserRegisterValuesDataToDomainMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginDomainToUiMapper() : UserRegisterValuesDomainToUiMapper<UserRegisterValuesUi> {
        return BaseUserRegisterValuesDomainToUiMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginUiToDomainMapper() : UserRegisterValuesUiToDomainMapper<UserRegisterValuesDomain> {
        return BaseUserRegisterValuesUiToDomainMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginDomainToDataMapper() : UserRegisterValuesDomainToDataMapper<UserRegisterValuesData> {
        return BaseUserRegisterValuesDomainToDataMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginDbToDataMapper() : UserLoginDbToDataMapper<UserRegisterValuesData> {
        return BaseUserRegisterValuesDbToDataMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginDataToDbMapper() : UserRegisterValuesDataToDbMapper<UserLoginDb> {
        return BaseUserRegisterValuesDataToDbMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginValuesUiToDomainMapper() : UserLoginValuesUiToDomainMapper<UserLoginValuesDomain> {
        return BaseUserLoginValuesUiToDomainMapper()
    }

    @AuthScope
    @Provides
    fun provideUserLoginValuesDomainToDataMapper() : UserLoginValuesDomainToDataMapper<UserLoginValuesData> {
        return BaseUserLoginDomainToDataMapper()
    }
}