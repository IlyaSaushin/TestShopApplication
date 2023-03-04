package com.earl.shop_feature.presentation.models

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.earl.utils.coreUi.Same

interface FlashSaleProductUi : Same<FlashSaleProductUi> {

    override fun same(value: FlashSaleProductUi) = this == value

    fun recyclerDetails(
        categoryText: TextView,
        nameText: TextView,
        priceText: TextView,
        discountTextView: TextView,
        image: ImageView
    )

    class Base(
        private val category: String,
        private val name: String,
        private val price: Double,
        private val discount: Int,
        private val imageUrl: String,
    ) : FlashSaleProductUi {

        override fun recyclerDetails(
            categoryText: TextView,
            nameText: TextView,
            priceText: TextView,
            discountTextView: TextView,
            image: ImageView
        ) {
            val context = categoryText.context
            categoryText.text = category
            nameText.text = name
            priceText.text = price.toString()
            discountTextView.text = "$discount off"
            Glide.with(context).load(imageUrl).into(image)
        }
    }
}