package com.optcrm.optreporting.app.view.adapters.Recycler.Holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.optcrm.optreporting.databinding.ItemRowBinding
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.retrofit.response.Response


/**
 * Created by swati on 24/9/2018.
 */
class ItemHolder(val context: Context, val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

  init {
    ArchitectureApp.instance.component.inject(this)
  }

  fun handleCard(position: Int, item: Response.RowData) {
    if (item.title!=null){
      binding.tvTitle.text=item.title
      binding.tvDescription.text=item.description
      Glide.with(context).load(item.imageHref).into(binding.ivItem)
    }



  }
}