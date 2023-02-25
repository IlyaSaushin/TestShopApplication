package com.earl.testshopapplication.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentLoginBinding
import com.earl.testshopapplication.presentation.core.BaseFragment

class LogInFragment : BaseFragment<FragmentLoginBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            navigator.mainPagerHost()
        }
    }

    companion object {
        fun newInstance() = LogInFragment()
    }
}