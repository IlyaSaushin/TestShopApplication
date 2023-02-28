package com.earl.shop_presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.earl.shop_presentation.databinding.FragmentHomeBinding
import com.earl.shop_presentation.di.ShopComponentProvider
import com.earl.shop_presentation.ui.recyclerAdapters.FlashSaleProductsRecyclerAdapter
import com.earl.shop_presentation.ui.recyclerAdapters.LatestProductsRecyclerAdapter
import com.earl.shop_presentation.ui.recyclerAdapters.ProductBrandsRecyclerAdapter
import com.earl.shop_presentation.ui.recyclerAdapters.ProductTypesRecyclerAdapter
import com.earl.utils.coreUi.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject lateinit var viewModel: ShopViewModel

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as ShopComponentProvider).provideShopComponent().inject(this)
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
        val adapter = FlashSaleProductsRecyclerAdapter()
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

    companion object {
        fun newInstance() = ShopFragment()
    }
}