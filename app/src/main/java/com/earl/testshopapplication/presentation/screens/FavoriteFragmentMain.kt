package com.earl.testshopapplication.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentFavoriteBinding
import com.earl.utils.coreUi.MainBaseFragment

class FavoriteFragmentMain : MainBaseFragment<FragmentFavoriteBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoriteBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance() = FavoriteFragmentMain()
    }
}