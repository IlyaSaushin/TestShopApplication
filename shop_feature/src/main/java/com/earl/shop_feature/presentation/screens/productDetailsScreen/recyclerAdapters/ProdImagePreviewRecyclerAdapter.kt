package com.earl.shop_feature.presentation.screens.productDetailsScreen.recyclerAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.earl.shop_feature.databinding.RecyclerProductImagePreviewBinding
import com.earl.shop_feature.databinding.RecyclerProductImagePreviewSelectedBinding
import com.earl.shop_feature.presentation.models.ProductDetailsPreviewImageWrapper

class ProdImagePreviewRecyclerAdapter(
    private val selectedIndex: (Int) -> Unit
) : ListAdapter<ProductDetailsPreviewImageWrapper, BaseProductImagePreviewViewHolder>(Diff) {

    private var selectedPos = RecyclerView.NO_POSITION

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).selected) {
            true -> SELECTED
            else -> NOT_SELECTED
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseProductImagePreviewViewHolder {
        return when(viewType) {
            SELECTED -> {
                BaseProductImagePreviewViewHolder.SelectedImage(
                    RecyclerProductImagePreviewSelectedBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            NOT_SELECTED -> {
                BaseProductImagePreviewViewHolder.NotSelectedImage(
                    RecyclerProductImagePreviewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalStateException("No such view type")
        }
    }

    override fun onBindViewHolder(holder: BaseProductImagePreviewViewHolder, position: Int) {
        if (selectedPos == RecyclerView.NO_POSITION) {
            getItem(position).selected = true
            selectedPos = 0
        }
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            selectedIndex.invoke(position)
            select(position)
        }
    }

    fun select(position: Int) {
        if (selectedPos == position) return
        getItem(selectedPos).selected = false
        notifyItemChanged(selectedPos)
        getItem(position).selected = true
        selectedPos = position
        notifyItemChanged(position)
    }

    companion object Diff : DiffUtil.ItemCallback<ProductDetailsPreviewImageWrapper>() {
        override fun areItemsTheSame(oldItem: ProductDetailsPreviewImageWrapper, newItem: ProductDetailsPreviewImageWrapper) =
            oldItem == newItem
        override fun areContentsTheSame(oldItem: ProductDetailsPreviewImageWrapper, newItem: ProductDetailsPreviewImageWrapper) =
            oldItem.equals(newItem)

        private const val SELECTED = 1
        private const val NOT_SELECTED = 0
    }
}

abstract class BaseProductImagePreviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ProductDetailsPreviewImageWrapper) {}

    class SelectedImage(
        private val binding: RecyclerProductImagePreviewSelectedBinding
    ) : BaseProductImagePreviewViewHolder(binding.root) {
        override fun bind(item: ProductDetailsPreviewImageWrapper) {
            Glide.with(binding.image.context).load(item.url).into(binding.image)
        }
    }

    class NotSelectedImage(
        private val binding: RecyclerProductImagePreviewBinding
    ) : BaseProductImagePreviewViewHolder(binding.root) {
        override fun bind(item: ProductDetailsPreviewImageWrapper) {
            Glide.with(binding.image.context).load(item.url).into(binding.image)
        }
    }
}