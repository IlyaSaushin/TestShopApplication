package com.earl.profile_presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.profile_domain.Interactor
import com.earl.profile_domain.LogOutOperationResultListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val interactor: Interactor
): ViewModel() {

    fun removeUserFromLocalDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val operationResult = interactor.removeUserDataFromLocalDb()
            when(operationResult) {
                is LogOutOperationResultListener.Success -> {}
                is LogOutOperationResultListener.Fail -> {}
            }
        }
    }
}