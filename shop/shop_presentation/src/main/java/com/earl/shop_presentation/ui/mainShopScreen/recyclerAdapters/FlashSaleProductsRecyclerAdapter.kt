package com.earl.shop_presentation.ui.mainShopScreen.recyclerAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.earl.shop_presentation.databinding.RecyclerFlashSaleItemBinding
import com.earl.shop_presentation.ui.models.FlashSaleProductUi
import com.earl.utils.coreUi.BaseRecyclerAdapter
import com.earl.utils.coreUi.BaseRecyclerViewHolder

interface OnFlashSaleProductClickListener {
    fun onFlashSaleProductClick()
}

class FlashSaleProductsRecyclerAdapter(
    private val clickListener: OnFlashSaleProductClickListener
) : BaseRecyclerAdapter<FlashSaleProductUi, BaseRecyclerViewHolder<FlashSaleProductUi>>(Diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<FlashSaleProductUi> {
        val binding = RecyclerFlashSaleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(private val binding: RecyclerFlashSaleItemBinding)
        : BaseRecyclerViewHolder<FlashSaleProductUi>(binding) {
        override fun bind(item: FlashSaleProductUi) {
            super.bind(item)
            itemView.setOnClickListener {
                clickListener.onFlashSaleProductClick()
            }
            item.recyclerDetails(
                binding.goodType,
                binding.goodTitle,
                binding.price,
                binding.sale,
                binding.image
            )
        }
    }

    companion object Diff : DiffUtil.ItemCallback<FlashSaleProductUi>() {
        override fun areItemsTheSame(oldItem: FlashSaleProductUi, newItem: FlashSaleProductUi) = oldItem.same(newItem)
        override fun areContentsTheSame(oldItem: FlashSaleProductUi, newItem: FlashSaleProductUi) = oldItem.equals(newItem)
    }
}