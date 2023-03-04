package com.earl.authorization_feature.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.authorization_feature.domain.Interactor
import com.earl.authorization_feature.domain.LoginOperationResult
import com.earl.authorization_feature.domain.models.UserLoginValuesDomain
import com.earl.authorization_feature.presentation.mappers.UserLoginValuesUiToDomainMapper
import com.earl.authorization_feature.presentation.models.UserLoginValuesUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val interactor: Interactor,
    private val loginValuesUiToDomainMapper: UserLoginValuesUiToDomainMapper<UserLoginValuesDomain>
) : ViewModel() {

    private val _successLoginOperation : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val successLoginOperation = _successLoginOperation.asStateFlow()

    private val _exceptionFlow: MutableStateFlow<List<Exception>> = MutableStateFlow(emptyList())
    val exceptionFlow = _exceptionFlow.asStateFlow()

    fun loginUser(userRegisterValuesUi: UserLoginValuesUi) {
        viewModelScope.launch(Dispatchers.IO) {
            when (
                val loginResult = interactor.loginUser(userRegisterValuesUi.mapToDomain(loginValuesUiToDomainMapper))
            ) {
                is LoginOperationResult.Success -> {
                    _successLoginOperation.value = true
                }
                is LoginOperationResult.Fail -> {
                    _exceptionFlow.value += loginResult.exception
                }
                else -> throw IllegalStateException("No such login operation result view type")
            }
        }
    }

    fun clearAuthorizationOperationResult() {
        _successLoginOperation.value = false
    }
}