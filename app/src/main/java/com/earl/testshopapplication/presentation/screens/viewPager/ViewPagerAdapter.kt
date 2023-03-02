package com.earl.testshopapplication.presentation.screens.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.earl.profile_presentation.ui.ProfileFragment
import com.earl.shop_presentation.ui.ShopHostFragment
import com.earl.testshopapplication.presentation.screens.BasketFragment
import com.earl.testshopapplication.presentation.screens.ChatFragment
import com.earl.testshopapplication.presentation.screens.FavoriteFragment

class ViewPagerAdapter(fa: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fa) {

    override fun getItemCount() = TABS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> list[0]
            1 -> list[1]
            2 -> list[2]
            3 -> list[3]
            else -> list[4]
        }
    }

    companion object {
        private const val TABS_COUNT = 5
    }
}