package com.earl.shop_feature.presentation.screens.productDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.shop_feature.domain.Interactor
import com.earl.shop_feature.domain.mappers.ProductDetailsDomainToUiMapper
import com.earl.shop_feature.presentation.models.ProductColorWrapper
import com.earl.shop_feature.presentation.models.ProductDetailsPreviewImageWrapper
import com.earl.shop_feature.presentation.models.ProductDetailsUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
    private val interactor: Interactor,
    private val productDetailsDomainToUiMapper: ProductDetailsDomainToUiMapper<ProductDetailsUi>
): ViewModel() {

    private val _prodDetails : MutableStateFlow<ProductDetailsUi?> = MutableStateFlow(null)
    val prodDetails = _prodDetails.asStateFlow()

    private val _prodDetailsImages : MutableStateFlow<List<ProductDetailsPreviewImageWrapper>> = MutableStateFlow(emptyList())
    val prodDetailsImages = _prodDetailsImages.asStateFlow()

    private val _prodDetailsColor : MutableStateFlow<List<ProductColorWrapper>> = MutableStateFlow(emptyList())
    val prodDetailsColor = _prodDetailsColor.asStateFlow()

    fun fetchProductDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val details = interactor.fetchProductDetails()
                .mapToUi(productDetailsDomainToUiMapper)
            _prodDetails.value = details
            _prodDetailsImages.value = details.provideImages()
            _prodDetailsColor.value = details.provideColors()
        }
    }
}