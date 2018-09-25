package com.optcrm.optreporting.app.view.adapters.Recycler.Holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.optcrm.optreporting.databinding.EmptyCommonListBinding

/**
 * Created by swati on 24/9/2018.
 */
class EmptyCommonListHolder(val context: Context,
    val binding: EmptyCommonListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun handleCard(position: Int, emptyText: String = "No Result Found") {
        binding.tvEmptyText.text = emptyText
    }
}