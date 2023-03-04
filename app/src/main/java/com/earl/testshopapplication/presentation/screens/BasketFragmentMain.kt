package com.earl.testshopapplication.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentBasketBinding
import com.earl.utils.coreUi.MainBaseFragment

class BasketFragmentMain : MainBaseFragment<FragmentBasketBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBasketBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance() = BasketFragmentMain()
    }
}