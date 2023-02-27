package com.earl.testshopapplication.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentHomeBinding
import com.earl.utils.coreUi.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance() = HomeFragment()
    }
}