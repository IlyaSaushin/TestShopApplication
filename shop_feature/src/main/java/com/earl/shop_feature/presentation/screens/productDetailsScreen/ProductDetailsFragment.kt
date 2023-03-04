package com.earl.shop_feature.presentation.screens.productDetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.earl.shop_feature.R
import com.earl.shop_feature.databinding.FragmentProductDetailsBinding
import com.earl.shop_feature.di.ShopComponentProvider
import com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters.CenterSnapHelper
import com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters.ProdColorsRecyclerAdapter
import com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters.ProdImagePreviewRecyclerAdapter
import com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters.ProdImageRecyclerAdapter
import com.earl.utils.coreUi.ShopBaseFragment
import com.earl.utils.navigation.NavigationContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailsFragment : ShopBaseFragment<FragmentProductDetailsBinding>() {

    @Inject lateinit var viewModel: ProductDetailsViewModel
    private lateinit var shopNavigator: NavigationContract.ShopNavigationContract
    private lateinit var mainImageRecyclerAdapter: ProdImageRecyclerAdapter
    private lateinit var previewImageRecyclerAdapter: ProdImagePreviewRecyclerAdapter
    private var changeImageFromPreview: Boolean = false
    private var quantityOfProduct = 1
    private var productPrice = 0

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductDetailsBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as ShopComponentProvider)
            .provideShopComponent().inject(this)
        shopNavigator = (requireParentFragment() as NavigationContract.ShopNavigationContract)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initImageRecyclerAdapter()
        initColorsRecyclerAdapter()
        initImagePreviewRecyclerAdapter()
        binding.backBtn.setOnClickListener { shopNavigator.back() }
    }

    private fun initViews() {
        if (viewModel.prodDetails.value == null) {
            viewModel.fetchProductDetails()
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.prodDetails.onEach { details ->
                    if (details != null) {
                        productPrice = details.price
                        binding.prodDetails.text = details.description
                        binding.productName.text = details.name
                        binding.prodRate.text = details.rate.toString()
                        binding.reviewsCount.text = String.format(
                            requireContext().getString(R.string.prod_reviews_count),
                            details.reviewsCount.toString()
                        )
                        binding.prodPrice.text = String.format(
                            requireContext().getString(R.string.prod_price),
                            details.price.toString()
                        )
                        binding.price.text = String.format(
                            requireContext().getString(com.earl.utils.R.string.price),
                            details.price.toString()
                        )
                        binding.quantityTv.text = String.format(
                            requireContext().getString(com.earl.utils.R.string.quantity),
                            quantityOfProduct.toString()
                        )
                    }
                    binding.plusBtn.setOnClickListener {
                        quantityOfProduct += 1
                        binding.quantityTv.text = String.format(
                            requireContext().getString(com.earl.utils.R.string.quantity),
                            quantityOfProduct.toString()
                        )
                        binding.price.text = String.format(
                            requireContext().getString(R.string.prod_price),
                            ((productPrice * quantityOfProduct)).toString()
                        )
                    }
                    binding.minusBtn.setOnClickListener {
                        if (quantityOfProduct == 1) return@setOnClickListener
                        quantityOfProduct -= 1
                        binding.quantityTv.text = String.format(
                            requireContext().getString(com.earl.utils.R.string.quantity),
                            quantityOfProduct.toString()
                        )
                        binding.price.text = String.format(
                            requireContext().getString(R.string.prod_price),
                            (productPrice * quantityOfProduct).toString()
                        )
                    }
                }.collect()
            }
        }
    }

    private fun initImageRecyclerAdapter() {
        mainImageRecyclerAdapter = ProdImageRecyclerAdapter()
        val snapHelper = CenterSnapHelper()
        binding.recyclerImages.adapter = mainImageRecyclerAdapter
        snapHelper.attachToRecyclerView(binding.recyclerImages)
        binding.recyclerImages.addOnScrollListener( object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lifecycleScope.launch {
                    if (!changeImageFromPreview) {
                        delay(700)
                        previewImageRecyclerAdapter.select(binding.recyclerImages.getCurrentPosition())
                    }
                }
            }
        })
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.prodDetails.onEach { details ->
                    if (details != null) {
                        mainImageRecyclerAdapter.submitList(details.images)
                    }
                }.collect()
            }
        }
    }

    private fun initImagePreviewRecyclerAdapter() {
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.recyclerImagesPreview.layoutManager = layoutManager
        previewImageRecyclerAdapter = ProdImagePreviewRecyclerAdapter { selectedPosition ->
            changeImageFromPreview = true
            binding.recyclerImages.layoutManager?.smoothScrollToPosition(
                binding.recyclerImages,
                null,
                selectedPosition
            )
            changeImageFromPreview = false
        }
        val snapHelper = CenterSnapHelper()
        binding.recyclerImagesPreview.adapter = previewImageRecyclerAdapter
        snapHelper.attachToRecyclerView(binding.recyclerImagesPreview)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.prodDetailsImages.onEach { details ->
                    if (details.isNotEmpty()) {
                        previewImageRecyclerAdapter.submitList(details)
                    }
                }.collect()
            }
        }
    }

    private fun initColorsRecyclerAdapter() {
        val adapter = ProdColorsRecyclerAdapter()
        binding.prodColorRecycler.adapter = adapter
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.prodDetailsColor.onEach { colors ->
                    if (colors.isNotEmpty()) {
                        adapter.submitList(colors)
                    }
                }.collect()
            }
        }
    }

    fun RecyclerView?.getCurrentPosition() : Int {
        return (this?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }
}