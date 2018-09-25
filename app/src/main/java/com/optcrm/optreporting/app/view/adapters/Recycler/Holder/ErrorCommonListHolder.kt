package com.optcrm.optreporting.app.view.adapters.Recycler.Holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.optcrm.optreporting.databinding.ErrorCommonListBinding

/**
 * Created by swati on 24/9/2018.
 */
class ErrorCommonListHolder(val context: Context,
    val binding: ErrorCommonListBinding) : RecyclerView.ViewHolder(binding.root) {

    private var mandatoryText = "Mandatory"

    fun handleCard(position: Int, emptyText: String = mandatoryText) {
        binding.tvErrorText.text = emptyText
    }
}