package com.earl.shop_feature.presentation.screens.mainShopScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.earl.shop_feature.R
import com.earl.shop_feature.databinding.FragmentHomeBinding
import com.earl.shop_feature.di.ShopComponentProvider
import com.earl.shop_feature.presentation.screens.mainShopScreen.recyclerAdapters.*
import com.earl.utils.coreUi.ShopBaseFragment
import com.earl.utils.navigation.NavigationContract
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopHomeFragment : ShopBaseFragment<FragmentHomeBinding>(), OnFlashSaleProductClickListener {

    @Inject lateinit var viewModel: ShopViewModel
    private lateinit var shopNavigator: NavigationContract.ShopNavigationContract
    private lateinit var autoSuggestTextViewAdapter: AutoSuggestTextViewAdapter

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as ShopComponentProvider)
            .provideShopComponent().inject(this)
        shopNavigator = (requireParentFragment() as NavigationContract.ShopNavigationContract)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerAdapter()
        initAutoCompleteTextView()
        viewModel.loadProductsData()
    }

    private fun initAutoCompleteTextView() {
        autoSuggestTextViewAdapter =
            AutoSuggestTextViewAdapter(
                requireContext(),
                R.layout.select_dialog_item
            ) {
                viewModel.fetchProductsBrands {
                    autoSuggestTextViewAdapter.setData(it)
                }
            }
        val autoCompleteWidget: AutoCompleteTextView = binding.searchEd
        autoCompleteWidget.threshold = 1
        autoCompleteWidget.setAdapter(autoSuggestTextViewAdapter)
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
        initBrandsRecyclerAdapter()
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

        fun newInstance() = ShopHomeFragment()
    }
}