package com.earl.shop_presentation.ui.screens.productDetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.earl.shop_presentation.R
import com.earl.shop_presentation.databinding.FragmentProductDetailsBinding
import com.earl.shop_presentation.di.ShopComponentProvider
import com.earl.shop_presentation.ui.screens.productDetailsScreen.recyclerAdapters.CenterSnapHelper
import com.earl.shop_presentation.ui.screens.productDetailsScreen.recyclerAdapters.ProdColorsRecyclerAdapter
import com.earl.shop_presentation.ui.screens.productDetailsScreen.recyclerAdapters.ProdImagePreviewRecyclerAdapter
import com.earl.shop_presentation.ui.screens.productDetailsScreen.recyclerAdapters.ProdImageRecyclerAdapter
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
        viewModel.fetchProductDetails()
        initViews()
        initImageRecyclerAdapter()
        initColorsRecyclerAdapter()
        initImagePreviewRecyclerAdapter()
        binding.backBtn.setOnClickListener { shopNavigator.back() }
    }

    private fun initViews() {
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
//            binding.recyclerImagesPreview.layoutManager?.smoothScrollToPosition(
//                binding.recyclerImagesPreview,
//                null,
//                selectedPosition
//            )
            changeImageFromPreview = true
            binding.recyclerImages.layoutManager?.smoothScrollToPosition(
                binding.recyclerImages,
                null,
                selectedPosition
            )
//            selectMiddleItem(layoutManager, binding.recyclerImagesPreview)

//            binding.recyclerImagesPreview[selectedPosition].elevation = 30f
//            Log.d("tag", "initImagePreviewRecyclerAdapter: $selectedPosition")
            changeImageFromPreview = false
        }

        val snapHelper = CenterSnapHelper()
//        binding.recyclerImagesPreview.addItemDecoration(CenterDecoration(1))
        binding.recyclerImagesPreview.adapter = previewImageRecyclerAdapter

        snapHelper.attachToRecyclerView(binding.recyclerImagesPreview)

//        binding.recyclerImagesPreview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                recyclerView.post {
////                    selectMiddleItem(layoutManager, recyclerView)
//
////                    val firstVisibleIndex = layoutManager.findFirstVisibleItemPosition()
////                    val lastVisibleIndex = layoutManager.findLastVisibleItemPosition()
////                    val visibleIndexes = listOf(firstVisibleIndex..lastVisibleIndex).flatten()
////
////                    for (i in visibleIndexes) {
////                        val vh = binding.recyclerImagesPreview.findViewHolderForLayoutPosition(i)
////                        if (vh?.itemView == null) {
////                            continue
////                        }
////                        val location = IntArray(2)
////                        vh.itemView.getLocationOnScreen(location)
////                        val x = location[0]
////                        val halfWidth = vh.itemView.width * .5
////                        val rightSide = x + halfWidth
////                        val leftSide = x - halfWidth
////                        val widthDp = resources.displayMetrics.run { widthPixels / density }
////                        val isInMiddle = widthDp * .5 in leftSide..rightSide
////                        if (isInMiddle) {
////                            // "i" is your middle index and implement selecting it as you want
//////                             optionsAdapter.selectItemAtIndex(i)
////                            binding.recyclerImagesPreview[i].elevation = 40f
////                            return@post
////                        }
////                    }
//                }
//            }
//        })
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

//    private fun selectMiddleItem(manager: LinearLayoutManager, recyclerView: RecyclerView) {
//
//        val firstVisibleIndex = manager.findFirstVisibleItemPosition()
//        val lastVisibleIndex = manager.findLastVisibleItemPosition()
//        val visibleIndexes = listOf(firstVisibleIndex..lastVisibleIndex).flatten()
//
//        for (k in visibleIndexes) {
//            val vh = recyclerView.findViewHolderForLayoutPosition(k)
//            if (vh?.itemView == null) {
//                continue
//            }
//            val location = IntArray(2)
//            vh.itemView.getLocationOnScreen(location)
//            val x = location[0]
//            val halfWidth = vh.itemView.width * .5
//            val rightSide = x + halfWidth
//            val leftSide = x - halfWidth
//            val isInMiddle = manager.width * .5 in leftSide..rightSide
//            if (isInMiddle) {
//
//                val mid = manager.width * 5
//                val d1 = 0.9f * mid
//                for (i in 0 until manager.childCount) {
//                    val child = manager.getChildAt(i)
////                    val childMid = (manager.getDecoratedRight(child!!) + manager.getDecoratedLeft(child)) / 0.2f
//                    val childMid = (manager.getDecoratedRight(child!!) + manager.getDecoratedLeft(child)) / 1.2f
//                    val d = Math.min(d1, Math.abs(mid - childMid))
//                    val scale = 1f - 0.15f * d / d1
//                    child.scaleX = scale
//                    child.scaleY = scale
//                    child.elevation = 40f
//                }
//                return
//            }
//        }
//    }

    //    private fun selectMiddleItem(layoutManager: LinearLayoutManager) {
//        val firstVisibleIndex = layoutManager.findFirstVisibleItemPosition()
//        val lastVisibleIndex = layoutManager.findLastVisibleItemPosition()
//        val visibleIndexes = listOf(firstVisibleIndex..lastVisibleIndex).flatten()
//
//        for (i in visibleIndexes) {
//            val vh = findViewHolderForLayoutPosition(i)
//            if (vh?.itemView == null) {
//                continue
//            }
//            val location = IntArray(2)
//            vh.itemView.getLocationOnScreen(location)
//            val x = location[0]
//            val halfWidth = vh.itemView.width * .5
//            val rightSide = x + halfWidth
//            val leftSide = x - halfWidth
//            val isInMiddle = screenWidth * .5 in leftSide..rightSide
//            if (isInMiddle) {
//                // "i" is your middle index and implement selecting it as you want
//                // optionsAdapter.selectItemAtIndex(i)
//                return
//            }
//        }
//    }

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