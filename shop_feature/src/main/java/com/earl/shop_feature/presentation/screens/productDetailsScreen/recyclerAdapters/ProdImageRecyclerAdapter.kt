package com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.earl.shop_feature.databinding.RecyclerProdDetailsImageItemBinding
import com.earl.utils.coreUi.BaseRecyclerAdapter
import com.earl.utils.coreUi.BaseRecyclerViewHolder

class ProdImageRecyclerAdapter : BaseRecyclerAdapter<String, BaseRecyclerViewHolder<String>>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<String> {
        val binding = RecyclerProdDetailsImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(private val binding: RecyclerProdDetailsImageItemBinding) :
        BaseRecyclerViewHolder<String>(binding) {
        override fun bind(item: String) {
            super.bind(item)
            Glide.with(binding.image.context).load(item).into(binding.image)
        }
    }

    companion object Diff : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem.equals(newItem)
    }
}