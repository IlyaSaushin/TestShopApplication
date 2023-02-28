package com.earl.testshopapplication.presentation.screens.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.earl.testshopapplication.R
import com.earl.testshopapplication.databinding.FragmentPagerHostBinding
import com.earl.utils.coreUi.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainPagerHostFragment : BaseFragment<FragmentPagerHostBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPagerHostBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val pagerAdapter = ViewPagerAdapter(this)
        val pager = binding.pager
        val tabs = binding.tabs
        pager.adapter = pagerAdapter
        pager.isUserInputEnabled = false;
        TabLayoutMediator(tabs, pager) { tab, pos ->
            when(pos) {
                0 -> tab.setIcon(R.drawable.home_tab)
                1 -> tab.setIcon(R.drawable.fav_tab)
                2 -> tab.setIcon(R.drawable.shop)
                3 -> tab.setIcon(R.drawable.chat_tab)
                4 -> tab.setIcon(R.drawable.profile_tab)
            }
        }.attach()
    }

    companion object {

        fun newInstance() = MainPagerHostFragment()
    }
}