package com.earl.testshopapplication.presentation.screens.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.earl.testshopapplication.presentation.screens.*

class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment.newInstance()
            1 -> FavoriteFragment.newInstance()
            2 -> BasketFragment.newInstance()
            3 -> ChatFragment.newInstance()
            else -> ProfileFragment.newInstance()
        }
    }

    companion object {
        private const val TABS_COUNT = 5
    }
}