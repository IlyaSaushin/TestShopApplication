package com.earl.testshopapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.earl.testshopapplication.R
import com.earl.testshopapplication.presentation.screens.navHosts.AuthNavHostFragment
import com.earl.utils.navigation.NavigationContract

class MainActivity : AppCompatActivity(), NavigationContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragmentWithoutBackStack(AuthNavHostFragment.newInstance())
    }

    private fun showFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, navHostAuth)
            .commit()
    }

    companion object {

        private const val navHostAuth = "navHostAuth"
    }
}