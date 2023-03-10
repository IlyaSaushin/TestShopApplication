package com.earl.shop_feature.presentation.screens.mainShopScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.shop_feature.domain.Interactor
import com.earl.shop_feature.domain.mappers.FlashSaleProductDomainToUiMapper
import com.earl.shop_feature.domain.mappers.LatestProductDomainToUiMapper
import com.earl.shop_feature.presentation.models.FlashSaleProductUi
import com.earl.shop_feature.presentation.models.GoodType
import com.earl.shop_feature.presentation.models.LatestProductUi
import com.earl.shop_feature.presentation.models.ProductBrand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val interactor: Interactor,
    private val flashSaleProductDomainToUiMapper: FlashSaleProductDomainToUiMapper<FlashSaleProductUi>,
    private val latestProductDomainToUiMapper: LatestProductDomainToUiMapper<LatestProductUi>
) : ViewModel() {

    private val _productsTypesList : MutableStateFlow<List<GoodType>> = MutableStateFlow(fetchProductTypesList())
    val productsTypesList = _productsTypesList.asStateFlow()

    private val _brandsList: MutableStateFlow<List<ProductBrand>> = MutableStateFlow(emptyList())
    val brandsList = _brandsList.asStateFlow()

    private val _flashSaleProductList : MutableStateFlow<List<FlashSaleProductUi>> = MutableStateFlow(emptyList())
    val flashSaleProductList = _flashSaleProductList.asStateFlow()

    private val _latestProductsList : MutableStateFlow<List<LatestProductUi>> = MutableStateFlow(emptyList())
    val latestProductsList = _latestProductsList.asStateFlow()

    fun loadProductsData() {
        viewModelScope.launch {
            var latestProductsList = listOf<LatestProductUi>()
            var flashSaleProductsList = listOf<FlashSaleProductUi>()
            var brandsList = listOf<ProductBrand>()
            viewModelScope.launch(Dispatchers.IO) {
                latestProductsList = fetchLatestProductsList()
                flashSaleProductsList = fetchFlashSaleProducts()
                brandsList = fetchProductBrands()
            }.join()
            _flashSaleProductList.value = flashSaleProductsList
            _latestProductsList.value = latestProductsList
            _brandsList.value = brandsList
        }
    }

    private suspend fun fetchFlashSaleProducts() = interactor.fetchFlashSaleProducts()
        .map { it.mapToUi(flashSaleProductDomainToUiMapper) }

    private suspend fun fetchLatestProductsList() = interactor.fetchLatestProducts()
        .map { it.mapToUi(latestProductDomainToUiMapper) }

    fun fetchProductsBrands(callback: (List<String>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val prodlist = interactor.fetchBrandsList()
            withContext(Dispatchers.Main) {
                callback.invoke(prodlist)
            }
        }
    }

    private fun fetchProductBrands() = listOf(
        ProductBrand(
            "Sport",
            "Adidas",
            com.earl.utils.R.drawable.adidas_logo
        ),
        ProductBrand(
            "Cars",
            "Nissan",
            com.earl.utils.R.drawable.nissan_logo
        ),
        ProductBrand(
            "Home",
            "Ikea",
            com.earl.utils.R.drawable.ikea_logo
        ),
        ProductBrand(
            "Phones",
            "Android",
            com.earl.utils.R.drawable.android_logo
        ),
    )

    private fun fetchProductTypesList() = listOf(
        GoodType(
            "Phones",
            com.earl.utils.R.drawable.phones
        ),
        GoodType(
            "Headphones",
            com.earl.utils.R.drawable.headphones
        ),
        GoodType(
            "Games",
            com.earl.utils.R.drawable.games
        ),
        GoodType("Cars", com.earl.utils.R.drawable.cars),
        GoodType(
            "Furniture",
            com.earl.utils.R.drawable.furniture
        ),
        GoodType("Kids", com.earl.utils.R.drawable.kids),
    )
}