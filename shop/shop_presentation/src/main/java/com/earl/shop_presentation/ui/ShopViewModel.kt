package com.earl.shop_presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.shop_domain.Interactor
import com.earl.shop_domain.mappers.FlashSaleProductDomainToUiMapper
import com.earl.shop_domain.mappers.LatestProductDomainToUiMapper
import com.earl.shop_presentation.ui.models.FlashSaleProductUi
import com.earl.shop_presentation.ui.models.GoodType
import com.earl.shop_presentation.ui.models.LatestProductUi
import com.earl.shop_presentation.ui.models.ProductBrand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShopViewModel @Inject constructor(
    private val interactor: Interactor,
    private val flashSaleProductDomainToUiMapper: FlashSaleProductDomainToUiMapper<FlashSaleProductUi>,
    private val latestProductDomainToUiMapper: LatestProductDomainToUiMapper<LatestProductUi>
) : ViewModel() {

    private val _productsTypesList : MutableStateFlow<List<GoodType>> = MutableStateFlow(listOf(
        GoodType("Phones", com.earl.utils.R.drawable.phones),
        GoodType("Headphones", com.earl.utils.R.drawable.headphones),
        GoodType("Games", com.earl.utils.R.drawable.games),
        GoodType("Cars", com.earl.utils.R.drawable.cars),
        GoodType("Furniture", com.earl.utils.R.drawable.furniture),
        GoodType("Kids", com.earl.utils.R.drawable.kids),
    ))
    val productsTypesList = _productsTypesList.asStateFlow()

    private val _brandsList: MutableStateFlow<List<ProductBrand>> = MutableStateFlow(listOf(
        ProductBrand("Sport", "Adidas", com.earl.utils.R.drawable.adidas_logo),
        ProductBrand("Cars", "Nissan", com.earl.utils.R.drawable.nissan_logo),
        ProductBrand("Home", "Ikea", com.earl.utils.R.drawable.ikea_logo),
        ProductBrand("Phones", "Android", com.earl.utils.R.drawable.android_logo),
    ))
    val brandsList = _brandsList.asStateFlow()

    private val _flashSaleProductList : MutableStateFlow<List<FlashSaleProductUi>> = MutableStateFlow(emptyList())
    val flashSaleProductList = _flashSaleProductList.asStateFlow()

    private val _latestProductsList : MutableStateFlow<List<LatestProductUi>> = MutableStateFlow(emptyList())
    val latestProductsList = _latestProductsList.asStateFlow()

    fun loadProductsData() {
        viewModelScope.launch {
            var latestProductsList = listOf<LatestProductUi>()
            var flashSaleProductsList = listOf<FlashSaleProductUi>()
            viewModelScope.launch(Dispatchers.IO) {
                latestProductsList = fetchLatestProductsList()
                flashSaleProductsList = fetchFlashSaleProducts()
            }.join()
            _flashSaleProductList.value = flashSaleProductsList
            _latestProductsList.value = latestProductsList
        }
    }

    private suspend fun fetchFlashSaleProducts() = interactor.fetchFlashSaleProducts()
        .map { it.mapToUi(flashSaleProductDomainToUiMapper) }

    private suspend fun fetchLatestProductsList() = interactor.fetchLatestProducts()
        .map { it.mapToUi(latestProductDomainToUiMapper) }
}