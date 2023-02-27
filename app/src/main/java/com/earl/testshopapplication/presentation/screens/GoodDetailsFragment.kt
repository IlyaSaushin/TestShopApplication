package com.earl.testshopapplication.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentGoodDetailsBinding
import com.earl.utils.coreUi.BaseFragment

class GoodDetailsFragment : BaseFragment<FragmentGoodDetailsBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGoodDetailsBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance() = GoodDetailsFragment()
    }
}