package com.earl.shop_presentation.ui.mainShopScreen.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.earl.shop_presentation.databinding.RecyclerBrandItemBinding
import com.earl.shop_presentation.ui.models.ProductBrand
import com.earl.utils.coreUi.BaseRecyclerAdapter
import com.earl.utils.coreUi.BaseRecyclerViewHolder

class ProductBrandsRecyclerAdapter : BaseRecyclerAdapter<ProductBrand, BaseRecyclerViewHolder<ProductBrand>>(
    Diff
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ProductBrand> {
        val binding = RecyclerBrandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(private val binding: RecyclerBrandItemBinding) :
        BaseRecyclerViewHolder<ProductBrand>(binding) {
        override fun bind(item: ProductBrand) {
            super.bind(item)
            binding.brandType.text = item.brandType
            binding.brandTitle.text = item.brandName
            binding.brandImage.setImageResource(item.logo)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<ProductBrand>() {
        override fun areItemsTheSame(oldItem: ProductBrand, newItem: ProductBrand) = oldItem.same(newItem)
        override fun areContentsTheSame(oldItem: ProductBrand, newItem: ProductBrand) = oldItem.equals(newItem)
    }
}