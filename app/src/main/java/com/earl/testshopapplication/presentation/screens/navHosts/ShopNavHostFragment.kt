package com.earl.testshopapplication.presentation.screens.navHosts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.earl.shop_feature.databinding.FragmentShopHostBinding
import com.earl.shop_feature.presentation.screens.mainShopScreen.ShopHomeFragment
import com.earl.shop_feature.presentation.screens.productDetailsScreen.ProductDetailsFragment
import com.earl.utils.coreUi.ShopBaseFragment
import com.earl.utils.navigation.NavigationContract

class ShopNavHostFragment : ShopBaseFragment<FragmentShopHostBinding>(),
    NavigationContract.ShopNavigationContract {

    private var lastScreen: String = shopScreen

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopHostBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(lastScreen) {
            shopScreen -> shopScreen()
            productDetailsScreen -> productDetailsScreen()
        }
    }

    override fun shopScreen() {
        childFragmentManager.findFragmentByTag(shopScreen).apply {
            if (this == null) {
                showFragment(ShopHomeFragment.newInstance(), shopScreen)
            } else {
                showFragment(this, shopScreen)
            }
            lastScreen = shopScreen
        }
    }

    override fun productDetailsScreen() {
        childFragmentManager.findFragmentByTag(productDetailsScreen).apply {
            if (this == null) {
                showFragment(ProductDetailsFragment.newInstance(), productDetailsScreen)

            } else {
                showFragment(this, productDetailsScreen)
            }
            lastScreen = productDetailsScreen
        }
    }

    override fun back() {
        childFragmentManager.popBackStack(shopScreen, 0)
        lastScreen = shopScreen
    }

    private fun showFragment(fragment: Fragment, fragmentTag: String) {
        childFragmentManager.beginTransaction()
            .replace(com.earl.shop_feature.R.id.shop_host_container, fragment, fragmentTag)
            .addToBackStack(fragmentTag)
            .commit()
    }

    companion object {

        fun newInstance() = ShopNavHostFragment()

        private const val shopScreen = "shopScreen"
        private const val productDetailsScreen = "productDetailsScreen"
    }
}