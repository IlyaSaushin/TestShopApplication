package com.earl.testshopapplication.presentation.screens.navHosts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.earl.profile_presentation.ui.ProfileFragment
import com.earl.shop_presentation.ui.screens.productDetailsScreen.ProductDetailsFragment
import com.earl.testshopapplication.R
import com.earl.testshopapplication.databinding.FragmentMainHostBinding
import com.earl.testshopapplication.presentation.screens.BasketFragmentMain
import com.earl.testshopapplication.presentation.screens.ChatFragmentMain
import com.earl.testshopapplication.presentation.screens.FavoriteFragmentMain
import com.earl.utils.coreUi.MainBaseFragment
import com.earl.utils.navigation.NavigationContract
import com.google.android.material.tabs.TabLayout

class MainNavHostFragment : MainBaseFragment<FragmentMainHostBinding>(),
    NavigationContract.BaseNavigationContract {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainHostBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        home()
    }

    override fun profile() {
        parentFragmentManager.findFragmentByTag(profileFrag).apply {
            if (this == null) {
                showFragmentWithBackStack(ProfileFragment.newInstance(), profile, profileFrag)
            } else {
                showFragmentWithBackStack(this, profile, profileFrag)
            }
        }
    }

    override fun home() {
        requireActivity().supportFragmentManager.findFragmentByTag(homeFrag).apply {
            if (this == null) {
                showFragmentWithBackStack(ShopNavHostFragment.newInstance(), home, shopNavHostFrag)
                Log.d("tag", "shop home not existed: $this")
            } else {
                showFragmentWithBackStack(this, home, shopNavHostFrag)
                Log.d("tag", "shop home fragment existed")
            }
        }
    }

    override fun favorite() {
        parentFragmentManager.findFragmentByTag(favoriteFrag).apply {
            if (this == null) {
                showFragmentWithBackStack(FavoriteFragmentMain.newInstance(), favorite, favoriteFrag)
            } else {
                showFragmentWithBackStack(this, favorite, favoriteFrag)
            }
        }
    }

    override fun chat() {
        parentFragmentManager.findFragmentByTag(chatFrag).apply {
            if (this == null) {
                showFragmentWithBackStack(ChatFragmentMain.newInstance(), chat, chatFrag)
            } else {
                showFragmentWithBackStack(this, chat, chatFrag)
            }
        }
    }

    override fun cart() {
        parentFragmentManager.findFragmentByTag(cartFrag).apply {
            if (this == null) {
                showFragmentWithBackStack(BasketFragmentMain.newInstance(), cart, cartFrag)
            } else {
                showFragmentWithBackStack(this, cart, cartFrag)
            }
        }
    }

    override fun signIn() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, AuthNavHostFragment.newInstance(), navHostAuth)
            .commit()
    }

    private fun initTabs() {
        val tabs = binding.tabs
        tabs.addTab(tabs.newTab().setIcon(R.drawable.home_tab))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.fav_tab))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.shop))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.chat_tab))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.profile_tab))
        tabs.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tabs.selectedTabPosition) {
                    0 -> home()
                    1 -> favorite()
                    2 -> cart()
                    3 -> chat()
                    4 -> profile()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        tabs.selectTab(tabs.getTabAt(0))
    }

    private fun showFragmentWithBackStack(fragment: Fragment, backStackTag: String, fragmentTag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_nav_host_container, fragment, fragmentTag)
            .addToBackStack(backStackTag)
            .commit()
    }

    companion object {

        fun newInstance() = MainNavHostFragment()
        private const val profile = "profile"
        private const val profileFrag = "profileFrag"
        private const val goodDetails = "goodDetails"
        private const val goodDetailsFrag = "goodDetailsFrag"
        private const val home = "home"
        private const val homeFrag = "homeFrag"
        private const val favorite = "favorite"
        private const val favoriteFrag = "favoriteFrag"
        private const val cart = "cart"
        private const val cartFrag = "cartFrag"
        private const val chat = "chat"
        private const val chatFrag = "chatFrag"
        private const val navHostAuth = "navHostAuth"
        private const val shopNavHostFrag = "shopNavHostFrag"
    }
}