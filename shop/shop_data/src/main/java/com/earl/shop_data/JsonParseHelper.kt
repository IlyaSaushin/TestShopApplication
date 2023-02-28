package com.earl.shop_data

import android.util.Log
import com.earl.shop_data.models.remote.FlashSaleProductRemote
import com.earl.shop_data.models.remote.LatestProductRemote
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

interface JsonParseHelper {

    fun parseToFlashSaleProduct(json: String) : List<FlashSaleProductRemote>

    fun parseToLatestProduct(json: String) : List<LatestProductRemote>

    class Base @Inject constructor() : JsonParseHelper {

        override fun parseToFlashSaleProduct(json: String): List<FlashSaleProductRemote> {
            Log.d("tag", "parseToFlashSaleProduct: $json")
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
            Log.d("tag", "parseToLatestProduct: $json")
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
    }
}