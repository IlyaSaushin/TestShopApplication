package com.earl.auth_presentation.prensentation.mappers

import com.earl.auth_domain.models.UserLoginValuesDomain
import javax.inject.Inject

class BaseUserLoginValuesUiToDomainMapper @Inject constructor() : UserLoginValuesUiToDomainMapper<UserLoginValuesDomain> {

    override fun map(firstName: String, email: String) =
        UserLoginValuesDomain.Base(firstName, email)
}