package com.earl.shop_presentation.ui.mainShopScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.earl.shop_presentation.databinding.FragmentHomeBinding
import com.earl.shop_presentation.di.ShopComponentProvider
import com.earl.shop_presentation.ui.ShopNavigationContract
import com.earl.shop_presentation.ui.mainShopScreen.recyclerAdapters.*
import com.earl.utils.coreUi.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopFragment : BaseFragment<FragmentHomeBinding>(), OnFlashSaleProductClickListener {

    @Inject lateinit var viewModel: ShopViewModel
    private lateinit var shopNavigator: ShopNavigationContract

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as ShopComponentProvider)
            .provideShopComponent().inject(this)
        shopNavigator = (requireParentFragment() as ShopNavigationContract)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerAdapter()
        viewModel.loadProductsData()
    }

    private fun initProductTypesRecyclerAdapter() {
        val adapter = ProductTypesRecyclerAdapter()
        binding.goodTypesRecycler.adapter = adapter
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.productsTypesList.onEach {
                    adapter.submitList(it)
                }.collect()
            }
        }
    }

    private fun initRecyclerAdapter() {
        initProductTypesRecyclerAdapter()
        initFlashSaleRecyclerAdapter()
        initLatestProductRecyclerAdapter()
    }

    private fun initFlashSaleRecyclerAdapter() {
        val adapter = FlashSaleProductsRecyclerAdapter(this)
        binding.flashSaleRecycler.adapter = adapter
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.flashSaleProductList.onEach {
                    adapter.submitList(it)
                }.collect()
            }
        }
    }

    private fun initLatestProductRecyclerAdapter() {
        val adapter = LatestProductsRecyclerAdapter()
        binding.latestRecycler.adapter = adapter
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.latestProductsList.onEach {
                    adapter.submitList(it)
                }.collect()
            }
        }
    }

    private fun initBrandsRecyclerAdapter() {
        val adapter = ProductBrandsRecyclerAdapter()
        binding.brandsRecycler.adapter = adapter
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.brandsList.onEach {
                    adapter.submitList(it)
                }.collect()
            }
        }
    }

    override fun onFlashSaleProductClick() {
        shopNavigator.productDetailsScreen()
    }

    companion object {
        fun newInstance() = ShopFragment()
    }
}