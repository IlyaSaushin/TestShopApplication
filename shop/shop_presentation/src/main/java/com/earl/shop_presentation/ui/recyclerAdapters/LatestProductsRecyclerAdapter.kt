package com.earl.shop_presentation.ui.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.earl.shop_presentation.databinding.RecyclerLatestItemBinding
import com.earl.shop_presentation.ui.models.LatestProductUi
import com.earl.utils.coreUi.BaseRecyclerAdapter
import com.earl.utils.coreUi.BaseRecyclerViewHolder

class LatestProductsRecyclerAdapter : BaseRecyclerAdapter<LatestProductUi, BaseRecyclerViewHolder<LatestProductUi>>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<LatestProductUi> {
        val binding =  RecyclerLatestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(private val binding: RecyclerLatestItemBinding) :
        BaseRecyclerViewHolder<LatestProductUi>(binding) {
        override fun bind(item: LatestProductUi) {
            super.bind(item)
            item.recyclerDetails(
                binding.goodType,
                binding.goodTitle,
                binding.price,
                binding.image
            )
        }
    }

    companion object Diff : DiffUtil.ItemCallback<LatestProductUi>() {
        override fun areItemsTheSame(oldItem: LatestProductUi, newItem: LatestProductUi) = oldItem.same(newItem)
        override fun areContentsTheSame(oldItem: LatestProductUi, newItem: LatestProductUi) = oldItem.equals(newItem)
    }
}