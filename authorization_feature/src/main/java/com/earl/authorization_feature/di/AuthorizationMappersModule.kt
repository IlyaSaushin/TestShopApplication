package com.earl.authorization_feature.di

import com.earl.authorization_feature.data.mappers.*
import com.earl.authorization_feature.data.models.UserLoginValuesData
import com.earl.authorization_feature.data.models.UserRegisterValuesData
import com.earl.authorization_feature.domain.mappers.UserLoginValuesDomainToDataMapper
import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToDataMapper
import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToUiMapper
import com.earl.authorization_feature.domain.models.UserLoginValuesDomain
import com.earl.authorization_feature.domain.models.UserRegisterValuesDomain
import com.earl.authorization_feature.presentation.mappers.*
import com.earl.authorization_feature.presentation.models.UserRegisterValuesUi
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
        return com.earl.authorization_feature.data.mappers.BaseUserRegisterValuesDbToDataMapper()
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