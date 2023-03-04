package com.earl.testshopapplication.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.testshopapplication.databinding.FragmentChatBinding
import com.earl.utils.coreUi.MainBaseFragment

class ChatFragmentMain : MainBaseFragment<FragmentChatBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentChatBinding.inflate(inflater, container, false)

    companion object {
        fun newInstance() = ChatFragmentMain()
    }
}