package com.earl.testshopapplication.presentation.screens.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.earl.profile_presentation.ui.ProfileFragment
import com.earl.testshopapplication.presentation.screens.*

class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount() = TABS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> com.earl.shop_presentation.ui.ShopFragment.newInstance()
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