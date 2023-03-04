package com.earl.utils.navigation

interface NavigationContract {

    interface BaseNavigationContract : NavigationContract {

        fun profile()

        fun home()

        fun favorite()

        fun chat()

        fun cart()

        fun signIn()
    }

    interface AuthNavigationContract : NavigationContract {

        fun signInFragment()

        fun loginFragment()

        fun mainHostFragment()
    }

    interface ShopNavigationContract : NavigationContract {

        fun shopScreen()

        fun productDetailsScreen()

        fun back()
    }
}