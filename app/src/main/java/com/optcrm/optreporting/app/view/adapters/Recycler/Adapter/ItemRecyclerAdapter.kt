package com.optcrm.optreporting.app.view.adapters.Recycler.Adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.optcrm.optreporting.R
import com.optcrm.optreporting.app.view.adapters.Recycler.Holder.EmptyCommonListHolder
import com.optcrm.optreporting.app.view.adapters.Recycler.Holder.ItemHolder
import com.optcrm.optreporting.databinding.EmptyCommonListBinding
import com.optcrm.optreporting.databinding.ItemRowBinding
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.development.interfaces.DataBaseUtil
import motobeans.architecture.retrofit.response.Response
import javax.inject.Inject

/**
 * Created by swati on 24/9/2018.
 */
class ItemRecyclerAdapter(val activity: FragmentActivity, val items: List<Response.RowData>?, val isCountToShow: Boolean = false) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @Inject
    lateinit var dataBase: DataBaseUtil

    init {
        ArchitectureApp.instance.component.inject(this)
    }

    val context: Context = activity

    private val DEFAULT_VIEW = -1
    private val EMPTY_VIEW = 0
    private val MAIN_ITEM_VISUAL = 1

    private fun getLayoutInflater(parent: ViewGroup?): LayoutInflater {
        return LayoutInflater.from(parent?.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            EMPTY_VIEW -> (holder as EmptyCommonListHolder).handleCard(position = position,
                emptyText = "No Item Found.")
            MAIN_ITEM_VISUAL -> (holder as ItemHolder).handleCard(position = position,
                item = items!![position])
            DEFAULT_VIEW -> (holder as ItemHolder).handleCard(position = position,
                item = items!![position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            EMPTY_VIEW -> {
                val binding: EmptyCommonListBinding = DataBindingUtil.inflate(
                    getLayoutInflater(parent), R.layout.empty_common_list, parent, false)
                viewHolder = EmptyCommonListHolder(context = activity, binding = binding)
            }
            MAIN_ITEM_VISUAL -> {
                val binding: ItemRowBinding = DataBindingUtil.inflate(
                    getLayoutInflater(parent),
                    R.layout.item_row, parent, false)
                viewHolder = ItemHolder(context = activity, binding = binding)
            }
            else -> {
                val binding: ItemRowBinding = DataBindingUtil.inflate(
                    getLayoutInflater(parent),
                    R.layout.item_row, parent, false)
                    viewHolder = ItemHolder(context = activity, binding = binding)
            }
        }
        return viewHolder
    }

    private var isEmpty = false
    override fun getItemCount(): Int {

        var size = items?.size ?: 0
        if (size == 0) {
            size = 1
            isEmpty = true
        } else {
            isEmpty = false
        }
        return size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && isEmpty) {
            return EMPTY_VIEW
        } else {
            return MAIN_ITEM_VISUAL
        }
    }
}
