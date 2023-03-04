package com.earl.shop_feature.data

import com.earl.shop_feature.data.models.remote.FlashSaleProductRemote
import com.earl.shop_feature.data.models.remote.LatestProductRemote
import com.earl.shop_feature.data.models.remote.ProductDetailsRemote
import org.json.JSONObject
import javax.inject.Inject

interface JsonParseHelper {

    fun parseToFlashSaleProduct(json: String) : List<FlashSaleProductRemote>

    fun parseToLatestProduct(json: String) : List<LatestProductRemote>

    fun parseToProductDetails(json: String) : ProductDetailsRemote

    fun parseToBrandsList(json: String) : List<String>

    class Base @Inject constructor() : JsonParseHelper {

        override fun parseToFlashSaleProduct(json: String): List<FlashSaleProductRemote> {
            val jsonObject = JSONObject(json)
            val productsArray = jsonObject.getJSONArray(flashSale)
            val readyList = mutableListOf<FlashSaleProductRemote>()
            for (i in 0 until productsArray.length()) {
                readyList.add(
                    FlashSaleProductRemote(
                        productsArray.getJSONObject(i).getString(flashSaleCategory),
                        productsArray.getJSONObject(i).getString(flashSaleName),
                        productsArray.getJSONObject(i).getDouble(flashSalePrice),
                        productsArray.getJSONObject(i).getInt(flashSaleDiscount),
                        productsArray.getJSONObject(i).getString(flashSaleImageUrl),
                    )
                )
            }
            return readyList
        }

        override fun parseToLatestProduct(json: String): List<LatestProductRemote> {
            val jsonObject = JSONObject(json)
            val productsArray = jsonObject.getJSONArray(latest)
            val readyList = mutableListOf<LatestProductRemote>()
            for (i in 0 until productsArray.length()) {
                readyList.add(
                    LatestProductRemote(
                        productsArray.getJSONObject(i).getString(latestCategory),
                        productsArray.getJSONObject(i).getString(latestName),
                        productsArray.getJSONObject(i).getInt(latestPrice),
                        productsArray.getJSONObject(i).getString(latestImageUrl),
                    )
                )
            }
            return readyList
        }

        override fun parseToProductDetails(json: String): ProductDetailsRemote {
            val jsonObject = JSONObject(json)
            val readyColors = mutableListOf<String>()
            val colors = jsonObject.getJSONArray(prodDetailsColors)
            for (i in 0 until colors.length()) {
                readyColors.add(colors.getString(i))
            }
            val readyImages = mutableListOf<String>()
            val images = jsonObject.getJSONArray(prodDetailsImages)
            for (i in 0 until colors.length()) {
                readyImages.add(images.getString(i))
            }
            return ProductDetailsRemote(
                jsonObject.getString(prodDetailsName),
                jsonObject.getString(prodDetailsDesc),
                jsonObject.getDouble(prodDetailsRate),
                jsonObject.getInt(prodDetailsReviewsCount),
                jsonObject.getInt(prodDetailsPrice),
                readyColors,
                readyImages,
            )
        }

        override fun parseToBrandsList(json: String): List<String> {
            val jsonArray = JSONObject(json).getJSONArray(brands)
            val readyList = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                readyList.add(jsonArray.getString(i))
            }
            return readyList
        }
    }

    companion object {
        private const val flashSale = "flash_sale"
        private const val flashSaleCategory = "category"
        private const val flashSaleName = "name"
        private const val flashSalePrice = "price"
        private const val flashSaleDiscount = "discount"
        private const val flashSaleImageUrl = "image_url"
        private const val latest = "latest"
        private const val latestCategory = "category"
        private const val latestName = "name"
        private const val latestPrice = "price"
        private const val latestImageUrl = "image_url"
        private const val prodDetailsName = "name"
        private const val prodDetailsDesc = "description"
        private const val prodDetailsRate = "rating"
        private const val prodDetailsReviewsCount = "number_of_reviews"
        private const val prodDetailsPrice = "price"
        private const val prodDetailsColors = "colors"
        private const val prodDetailsImages = "image_urls"
        private const val brands = "words"
    }
}

