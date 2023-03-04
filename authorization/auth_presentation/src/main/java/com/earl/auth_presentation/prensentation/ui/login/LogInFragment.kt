package com.earl.auth_presentation.prensentation.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.earl.auth_presentation.R
import com.earl.auth_presentation.databinding.FragmentLoginBinding
import com.earl.auth_presentation.di.AuthComponentProvider
import com.earl.auth_presentation.prensentation.models.UserLoginValuesUi
import com.earl.utils.coreUi.AuthBaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInFragment : AuthBaseFragment<FragmentLoginBinding>() {

    @Inject lateinit var viewModel: LoginViewModel

    @Inject lateinit var validation: LoginFormValidation

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as AuthComponentProvider)
            .provideAuthComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoginOperationResultListeners()
        binding.loginBtn.setOnClickListener { login() }
    }

    private fun login() {
        if (validation.validate(binding.firstNameEditText, binding.passwordEditText)) {
            viewModel.loginUser(
                UserLoginValuesUi.Base(
                    binding.firstNameEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            )
        }
    }

    private fun initLoginOperationResultListeners() {
        initExceptionHandler()
        initSuccessfulLoginOperationHandler()
    }

    private fun initSuccessfulLoginOperationHandler() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.successLoginOperation.onEach { success ->
                    if (success) {
                        navigator.mainHostFragment()
                        viewModel.clearAuthorizationOperationResult()
                    }
                }.collect()
            }
        }
    }

    private fun initExceptionHandler() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.exceptionFlow.onEach { exceptionList ->
                    if (exceptionList.isNotEmpty()) {
                        showAlertDialogWithExceptionMessage(exceptionList.last().message
                            ?: getString(R.string.registration_error_try_again))
                    }
                }.collect()
            }
        }
    }

    private fun showAlertDialogWithExceptionMessage(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(message)
        builder.setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
            dialog.cancel()
        }
        builder.create()
        builder.show()
    }

    companion object {
        fun newInstance() = LogInFragment()
    }
}