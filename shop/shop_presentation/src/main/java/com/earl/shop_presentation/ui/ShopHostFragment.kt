package com.earl.shop_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.earl.shop_presentation.R
import com.earl.shop_presentation.databinding.FragmentShopHostBinding
import com.earl.shop_presentation.ui.mainShopScreen.ShopFragment
import com.earl.shop_presentation.ui.productDetails.ProductDetailsFragment
import com.earl.utils.coreUi.BaseFragment
import javax.inject.Inject

class ShopHostFragment @Inject constructor() : BaseFragment<FragmentShopHostBinding>(), ShopNavigationContract {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopHostBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopScreen()
    }

    override fun shopScreen() {
        showFragment(ShopFragment.newInstance())
    }

    override fun productDetailsScreen() {
        showFragment(ProductDetailsFragment.newInstance())
    }

    override fun back() {
        childFragmentManager.popBackStack()
    }

    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.shop_host_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = ShopHostFragment()
    }
}