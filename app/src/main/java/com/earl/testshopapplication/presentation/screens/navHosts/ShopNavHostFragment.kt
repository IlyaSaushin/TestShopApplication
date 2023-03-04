package com.earl.testshopapplication.presentation.screens.navHosts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.earl.shop_presentation.R
import com.earl.shop_presentation.databinding.FragmentShopHostBinding
import com.earl.shop_presentation.ui.screens.mainShopScreen.ShopHomeFragment
import com.earl.shop_presentation.ui.screens.productDetailsScreen.ProductDetailsFragment
import com.earl.utils.coreUi.ShopBaseFragment
import com.earl.utils.navigation.NavigationContract

class ShopNavHostFragment : ShopBaseFragment<FragmentShopHostBinding>(),
    NavigationContract.ShopNavigationContract {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopHostBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopScreen()
    }

    override fun shopScreen() {
        childFragmentManager.findFragmentByTag(shopScreen).apply {
            if (this == null) {
                Log.d("tag", "shopScreen: not existed -> $this")
                showFragment(ShopHomeFragment.newInstance(), shopScreen)
            } else {
                Log.d("tag", "shopScreen: existed -> $this")
                showFragment(this, shopScreen)
            }
        }
    }

    override fun productDetailsScreen() {
        childFragmentManager.findFragmentByTag(productDetailsScreen).apply {
            if (this == null) {
                showFragment(ProductDetailsFragment.newInstance(), productDetailsScreen)
                Log.d("tag", "productDetails: not existed -> $this")

            } else {
                showFragment(this, productDetailsScreen)
                Log.d("tag", "productDetails: existed -> $this")
            }
        }
    }

    override fun back() {
        childFragmentManager.popBackStack()
    }

    private fun showFragment(fragment: Fragment, fragmentTag: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.shop_host_container, fragment, fragmentTag)
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance() = ShopNavHostFragment()

        private const val shopScreen = "shopScreen"
        private const val productDetailsScreen = "productDetailsScreen"
    }
}