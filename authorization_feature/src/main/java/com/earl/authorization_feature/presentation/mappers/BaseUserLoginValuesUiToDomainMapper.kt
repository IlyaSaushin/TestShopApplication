package com.earl.authorization_feature.presentation.mappers

import com.earl.authorization_feature.domain.models.UserLoginValuesDomain
import javax.inject.Inject

class BaseUserLoginValuesUiToDomainMapper @Inject constructor() :
    UserLoginValuesUiToDomainMapper<UserLoginValuesDomain> {

    override fun map(firstName: String, email: String) =
        UserLoginValuesDomain.Base(firstName, email)
}