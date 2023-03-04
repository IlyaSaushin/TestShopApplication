package com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.earl.shop_feature.databinding.RecyclerProdColorItemBinding
import com.earl.shop_feature.databinding.RecyclerProdColorSelectedItemBinding
import com.earl.shop_feature.presentation.models.ProductColorWrapper

class ProdColorsRecyclerAdapter : ListAdapter<ProductColorWrapper, BaseProductColorViewHolder>(Diff) {

    private var selectedPos = RecyclerView.NO_POSITION

    override fun getItemViewType(position: Int) =
        when(getItem(position).isSelected) {
            true -> SELECTED
            else -> NOT_SELECTED
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType) {
            SELECTED -> {
                BaseProductColorViewHolder.SelectedColor(
                    RecyclerProdColorSelectedItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            NOT_SELECTED -> {
                BaseProductColorViewHolder.NotSelectedColor(
                    RecyclerProdColorItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalStateException("No such view type")
        }

    override fun onBindViewHolder(holder: BaseProductColorViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (selectedPos == RecyclerView.NO_POSITION) {
            getItem(position).isSelected = true
            selectedPos = 0
        }
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            this.select(position)
        }
    }

    private fun select(position: Int) {
        if (selectedPos == position) return
        getItem(selectedPos).isSelected = false
        notifyItemChanged(selectedPos)
        getItem(position).isSelected = true
        selectedPos = position
        notifyItemChanged(position)
    }

    companion object Diff : DiffUtil.ItemCallback<ProductColorWrapper>() {
        override fun areItemsTheSame(oldItem: ProductColorWrapper, newItem: ProductColorWrapper) =
            oldItem == newItem
        override fun areContentsTheSame(oldItem: ProductColorWrapper, newItem: ProductColorWrapper) =
            oldItem.equals(newItem)

        private const val SELECTED = 1
        private const val NOT_SELECTED = 0
    }
}

abstract class BaseProductColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ProductColorWrapper) {}

    class SelectedColor(
        private val binding: RecyclerProdColorSelectedItemBinding
    ) : BaseProductColorViewHolder(binding.root) {
        override fun bind(item: ProductColorWrapper) {
            binding.color.setBackgroundColor(Color.parseColor(item.color))
        }
    }
    class NotSelectedColor(
        private val binding: RecyclerProdColorItemBinding
    ) : BaseProductColorViewHolder(binding.root) {
        override fun bind(item: ProductColorWrapper) {
            binding.color.setBackgroundColor(Color.parseColor(item.color))
        }
    }
}