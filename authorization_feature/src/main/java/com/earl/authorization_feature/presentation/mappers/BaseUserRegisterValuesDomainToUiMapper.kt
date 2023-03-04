package com.earl.authorization_feature.presentation.mappers

import com.earl.authorization_feature.domain.mappers.UserRegisterValuesDomainToUiMapper
import com.earl.authorization_feature.presentation.models.UserRegisterValuesUi
import javax.inject.Inject

class BaseUserRegisterValuesDomainToUiMapper @Inject constructor():
    UserRegisterValuesDomainToUiMapper<UserRegisterValuesUi> {

    override fun mapToUi(firstName: String, password: String, email: String) =
        UserRegisterValuesUi.Base(firstName, password, email)
}