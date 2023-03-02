package com.earl.shop_presentation.ui.mainShopScreen.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.earl.shop_presentation.databinding.RecyclerGoodTypesBinding
import com.earl.shop_presentation.ui.models.GoodType
import com.earl.utils.coreUi.BaseRecyclerAdapter
import com.earl.utils.coreUi.BaseRecyclerViewHolder

class ProductTypesRecyclerAdapter : BaseRecyclerAdapter<GoodType, BaseRecyclerViewHolder<GoodType>>(
    Diff
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(
        RecyclerGoodTypesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
    )

    inner class ItemViewHolder(private val binding: RecyclerGoodTypesBinding)
        : BaseRecyclerViewHolder<GoodType>(binding) {
        override fun bind(item: GoodType) {
            super.bind(item)
            binding.goodType.text = item.goodType
            binding.image.setImageResource(item.image)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<GoodType>() {
        override fun areItemsTheSame(oldItem: GoodType, newItem: GoodType) = oldItem.same(newItem)
        override fun areContentsTheSame(oldItem: GoodType, newItem: GoodType) = oldItem.equals(newItem)
    }
}