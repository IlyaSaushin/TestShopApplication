package com.earl.shop_feature.presentation.models

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.earl.utils.coreUi.Same

interface LatestProductUi : Same<LatestProductUi> {

    override fun same(value: LatestProductUi) = this == value

    fun recyclerDetails(
        categoryText: TextView,
        nameText: TextView,
        priceText: TextView,
        image: ImageView
    )

    class Base(
        private val category: String,
        private val name: String,
        private val price: Int,
        private val imageUrl: String
    ) : LatestProductUi {

        override fun recyclerDetails(
            categoryText: TextView,
            nameText: TextView,
            priceText: TextView,
            image: ImageView
        ) {
            categoryText.text = category
            nameText.text = name
            priceText.text = price.toString()
            Glide.with(image.context).load(imageUrl).into(image)
        }
    }
}