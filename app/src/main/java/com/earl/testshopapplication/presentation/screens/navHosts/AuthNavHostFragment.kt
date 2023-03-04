package com.earl.testshopapplication.presentation.screens.navHosts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.earl.authorization_feature.presentation.ui.login.LogInFragment
import com.earl.authorization_feature.presentation.ui.signIn.SignInFragment
import com.earl.testshopapplication.R
import com.earl.testshopapplication.databinding.FragmentHostAuthBinding
import com.earl.utils.coreUi.AuthBaseFragment
import com.earl.utils.navigation.NavigationContract

class AuthNavHostFragment : AuthBaseFragment<FragmentHostAuthBinding>(),
    NavigationContract.AuthNavigationContract {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHostAuthBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInFragment()
    }

    override fun signInFragment() {
        parentFragmentManager.findFragmentByTag(signInFrag).apply {
            if (this == null) {
                showFragmentWithoutBackStack(SignInFragment.newInstance(), signInFrag)
            } else {
                showFragmentWithoutBackStack(this, signInFrag)
            }
        }
    }

    override fun loginFragment() {
        parentFragmentManager.findFragmentByTag(logInFrag).apply {
            if (this == null) {
                showFragmentWithBackStack(LogInFragment.newInstance(), logIn, logInFrag)
            } else {
                showFragmentWithBackStack(this, logIn, logInFrag)
            }
        }
    }

    override fun mainHostFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainNavHostFragment.newInstance(), mainHostFrag)
            .commit()
    }

    private fun showFragmentWithBackStack(fragment: Fragment, backStackTag: String, fragmentTag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.auth_container, fragment, fragmentTag)
            .addToBackStack(backStackTag)
            .commit()
    }

    private fun showFragmentWithoutBackStack(fragment: Fragment, fragmentTag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.auth_container, fragment, fragmentTag)
            .commit()
    }

    companion object {

        fun newInstance() = AuthNavHostFragment()

        private const val logIn = "logIn"
        private const val logInFrag = "logInFrag"
        private const val signInFrag = "signInFrag"
        private const val mainHostFrag = "mainHostFrag"
    }
}