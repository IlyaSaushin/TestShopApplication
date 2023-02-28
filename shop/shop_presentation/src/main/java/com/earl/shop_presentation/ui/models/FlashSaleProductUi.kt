package com.earl.shop_presentation.ui.models

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.earl.shop_presentation.R
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
//            discountTextView.text = String.format(context.resources.getString(com.earl.utils.R.string.sale_off), discount)
            discountTextView.text = "$discount off"
            Glide.with(context).load(imageUrl).into(image)
        }
    }
}