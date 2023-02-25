package com.earl.testshopapplication.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentSignInBinding
import com.earl.testshopapplication.presentation.core.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignInBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginScreenBtn.setOnClickListener {
            navigator.loginFragment()
        }
        binding.signInBtn.setOnClickListener {
            navigator.mainPagerHost()
        }
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}