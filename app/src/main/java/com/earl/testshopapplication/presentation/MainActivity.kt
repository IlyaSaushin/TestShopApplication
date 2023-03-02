package com.earl.testshopapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.earl.auth_presentation.prensentation.ui.login.LogInFragment
import com.earl.auth_presentation.prensentation.ui.signIn.SignInFragment
import com.earl.profile_presentation.ui.ProfileFragment
import com.earl.shop_presentation.ui.ShopHostFragment
import com.earl.testshopapplication.R
import com.earl.testshopapplication.presentation.screens.GoodDetailsFragment
import com.earl.testshopapplication.presentation.screens.viewPager.MainPagerHostFragment
import com.earl.utils.coreUi.NavigationContract

class MainActivity : AppCompatActivity(), NavigationContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signInFragment()
    }

    override fun signInFragment() {
        showFragmentWithoutBackStack(SignInFragment.newInstance())
    }

    override fun loginFragment() {
        showFragmentWithBackStack(LogInFragment.newInstance(), logIn)
    }

    override fun profile() {
        showFragmentWithBackStack(ProfileFragment.newInstance(), profile)
    }

    override fun home() {
        showFragmentWithBackStack(ShopHostFragment.newInstance(), home)
    }

    override fun goodDetails() {
        showFragmentWithBackStack(GoodDetailsFragment.newInstance(), goodDetails)
    }

    override fun mainPagerHost() {
        showFragmentWithoutBackStack(MainPagerHostFragment.newInstance())
    }

    private fun showFragmentWithBackStack(fragment: Fragment, backStackTag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(backStackTag)
            .commit()
    }

    private fun showFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    companion object {

        private const val logIn = "logIn"
        private const val profile = "profile"
        private const val goodDetails = "goodDetails"
        private const val home = "home"
        private const val productDetails = "productDetails"
    }
}