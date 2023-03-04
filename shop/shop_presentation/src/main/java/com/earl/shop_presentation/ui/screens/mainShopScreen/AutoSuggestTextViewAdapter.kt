package com.earl.shop_presentation.ui.screens.mainShopScreen

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable

class AutoSuggestAdapter(context: Context, resource: Int, callback: (String) -> Unit) :
    ArrayAdapter<String>(context, resource), Filterable {

    var mlistData: MutableList<String> = mutableListOf()
    var fetchCallback: (String) -> Unit

    init {
        fetchCallback = callback
    }

    fun setData(list: List<String>?) {
        mlistData.clear()
        mlistData.addAll(list!!)
    }

    override fun getCount(): Int {
        return mlistData.size
    }

    override fun getItem(position: Int): String {
        return mlistData[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    fetchCallback.invoke("")
                    filterResults.values = mlistData.filter { it.startsWith(constraint) }
                    filterResults.count = mlistData.size
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (constraint != null) {
                    if (results != null && results.count > 0) {
                        mlistData = (results.values as ArrayList<String>)
                        notifyDataSetChanged()
                    } else {
                        notifyDataSetInvalidated()
                    }
                }
            }
        }
    }
}