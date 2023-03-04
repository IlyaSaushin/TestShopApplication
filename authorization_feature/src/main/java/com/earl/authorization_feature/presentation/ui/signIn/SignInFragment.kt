package com.earl.authorization_feature.presentation.ui.signIn

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.earl.authorization_feature.R
import com.earl.authorization_feature.databinding.FragmentSignInBinding
import com.earl.authorization_feature.di.AuthComponentProvider
import com.earl.authorization_feature.presentation.models.UserRegisterValuesUi
import com.earl.utils.coreUi.AuthBaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInFragment : AuthBaseFragment<FragmentSignInBinding>() {

    @Inject lateinit var viewModel: SignInViewModel

    @Inject lateinit var validation: UserRegistrationFormValidation

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignInBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authComponent = (requireActivity().applicationContext as AuthComponentProvider)
            .provideAuthComponent()
        authComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRegistrationResultListeners()
        binding.loginScreenBtn.setOnClickListener { navigator.loginFragment() }
        binding.signInBtn.setOnClickListener { registerNewUser() }
    }

    private fun registerNewUser() {
        if (validation.validate(binding.firstNameEd, binding.lastNameEd, binding.emailEd)) {
            viewModel.insertNewUserIntoLocalDb(
                UserRegisterValuesUi.Base(
                    binding.firstNameEd.text.toString(),
                    binding.lastNameEd.text.toString(),
                    binding.emailEd.text.toString()
                )
            )
        }
    }

    private fun initRegistrationResultListeners() {
        initExceptionHandler()
        initRegistrationSuccessHandler()
        initUserAlreadyExistsException()
    }

    private fun initExceptionHandler() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.exceptionFlow.onEach { exception ->
                    if (exception.isNotEmpty()) {
                        showAlertDialogWithExceptionMessage(exception.last().message
                            ?: getString(R.string.registration_error_try_again))
                    }
                }.collect()
            }
        }
    }

    private fun initRegistrationSuccessHandler() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registrationSuccessResultFlow.onEach { successfulResult ->
                    if (successfulResult) {
                        navigator.mainHostFragment()
                        viewModel.clearAuthorizationOperationResult()
                    }
                }.collect()
            }
        }
    }

    private fun initUserAlreadyExistsException() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userAlreadyExistsExceptionFlow.onEach { exception ->
                    if (exception.isNotEmpty()) {
                        showAlertDialogWithExceptionMessage(getString(com.earl.utils.R.string.user_is_already_registered))
                    }
                }.collect()
            }
        }
    }

    private fun showAlertDialogWithExceptionMessage(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(message)
            .setPositiveButton(
                com.earl.utils.R.string.try_again
            ) { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}