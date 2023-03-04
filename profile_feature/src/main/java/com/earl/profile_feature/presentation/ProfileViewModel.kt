package com.earl.profile_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.profile_feature.domain.Interactor
import com.earl.profile_feature.domain.LogOutOperationResultListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val interactor: Interactor
): ViewModel() {

    fun removeUserFromLocalDb() {
        viewModelScope.launch(Dispatchers.IO) {
            when(interactor.removeUserDataFromLocalDb()) {
                is LogOutOperationResultListener.Success -> {}
                is LogOutOperationResultListener.Fail -> {}
            }
        }
    }
}