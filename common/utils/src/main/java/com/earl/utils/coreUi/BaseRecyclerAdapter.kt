package com.earl.utils.coreUi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseRecyclerAdapter<T, V : BaseRecyclerViewHolder<T>>(diff: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, V>(diff) {

    override fun onBindViewHolder(holder: V, position: Int) =
        holder.bind(getItem(position))

    protected fun Int.createView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}