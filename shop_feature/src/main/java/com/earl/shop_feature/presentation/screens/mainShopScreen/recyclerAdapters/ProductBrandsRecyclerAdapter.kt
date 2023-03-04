package com.earl.shop_feature.presentation.screens.mainShopScreen.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.earl.shop_feature.databinding.RecyclerBrandBinding
import com.earl.shop_feature.presentation.models.ProductBrand
import com.earl.utils.coreUi.BaseRecyclerAdapter
import com.earl.utils.coreUi.BaseRecyclerViewHolder

class ProductBrandsRecyclerAdapter :
    BaseRecyclerAdapter<ProductBrand, BaseRecyclerViewHolder<ProductBrand>>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ProductBrand> {
        val binding = RecyclerBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(private val binding: RecyclerBrandBinding) :
        BaseRecyclerViewHolder<ProductBrand>(binding) {
        override fun bind(item: ProductBrand) {
            super.bind(item)
            binding.image.setImageResource(item.logo)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<ProductBrand>() {
        override fun areItemsTheSame(oldItem: ProductBrand, newItem: ProductBrand) =
            oldItem.same(newItem)
        override fun areContentsTheSame(oldItem: ProductBrand, newItem: ProductBrand) =
            oldItem.equals(newItem)
    }
}