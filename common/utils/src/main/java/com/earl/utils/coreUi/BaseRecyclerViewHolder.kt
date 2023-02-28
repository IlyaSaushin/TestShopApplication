package com.earl.utils.coreUi

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: T) {}
}