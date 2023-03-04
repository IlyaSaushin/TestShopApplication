package com.earl.authorization_feature.presentation.ui.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.authorization_feature.domain.Interactor
import com.earl.authorization_feature.domain.RegistrationOperationExceptions
import com.earl.authorization_feature.domain.RegistrationOperationResult
import com.earl.authorization_feature.domain.models.UserRegisterValuesDomain
import com.earl.authorization_feature.presentation.mappers.UserRegisterValuesUiToDomainMapper
import com.earl.authorization_feature.presentation.models.UserRegisterValuesUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val interactor: Interactor,
    private val userRegisterValuesUiToDomainMapper: UserRegisterValuesUiToDomainMapper<UserRegisterValuesDomain>
) : ViewModel() {

    private val _exceptionFlow: MutableStateFlow<List<Exception>> = MutableStateFlow(emptyList())
    val exceptionFlow = _exceptionFlow.asStateFlow()

    private val _registrationSuccessResultFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val registrationSuccessResultFlow = _registrationSuccessResultFlow.asStateFlow()

    private val _userAlreadyExistsExceptionFlow: MutableStateFlow<List<Exception>> = MutableStateFlow(emptyList())
    val userAlreadyExistsExceptionFlow = _userAlreadyExistsExceptionFlow.asStateFlow()

    fun insertNewUserIntoLocalDb(userRegisterValuesUi: UserRegisterValuesUi) {
        viewModelScope.launch(Dispatchers.IO) {
            when(
                val registerResult = interactor.insertNewUserIntoLocalDb(
                    userRegisterValuesUi.mapToDomain(userRegisterValuesUiToDomainMapper)
                )
            ) {
                is RegistrationOperationResult.Success -> {
                    _registrationSuccessResultFlow.value = true
                }
                is RegistrationOperationResult.Fail -> {
                    if (registerResult.exception.message ==
                        RegistrationOperationExceptions.USER_IS_ALREADY_REGISTERED.message) {
                        _userAlreadyExistsExceptionFlow.value += registerResult.exception
                    } else {
                        _exceptionFlow.value += registerResult.exception
                    }
                }
                else -> throw IllegalStateException("No such registration operation result type")
            }
        }
    }

    fun clearAuthorizationOperationResult() {
        _registrationSuccessResultFlow.value = false
    }
}