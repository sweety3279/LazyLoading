package com.optcrm.optreporting.app.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.optcrm.optreporting.R
import com.optcrm.optreporting.app.presenter.connector.MainViewConnector
import com.optcrm.optreporting.app.presenter.presenter.MainViewPresenter
import com.optcrm.optreporting.app.view.adapters.Recycler.Adapter.EndlessRecyclerViewScrollListener
import com.optcrm.optreporting.app.view.adapters.Recycler.Adapter.ItemRecyclerAdapter
import com.optcrm.optreporting.databinding.ActivityMainBinding
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.customAppComponents.activity.BaseAppCompatActivity
import motobeans.architecture.retrofit.response.Response
import motobeans.architecture.retrofit.response.Response.ResponseData
import motobeans.architecture.util.delegates.ActivityBindingProviderDelegate
import motobeans.architecture.util.exGone
import motobeans.architecture.util.exVisible


/**
 * Created by swati on 24/9/2018.
 */

class MainActivity : BaseAppCompatActivity(), MainViewConnector.ViewOpt {

  companion object {
    fun start(context: Context) {
      val intent = Intent(context, MainActivity::class.java)
      context.startActivity(intent)
    }
  }


  private var alItem = ArrayList<Response.RowData>()


  lateinit var presenter: MainViewConnector.PresenterOpt
  private val binding: ActivityMainBinding by ActivityBindingProviderDelegate(this,
      R.layout.activity_main)


  private val linearLayoutManager = LinearLayoutManager(this)

  override fun init() {
    hideBack()
    ArchitectureApp.instance.component.inject(this)
    presenter = MainViewPresenter(viewOpt = this)

    var itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)
//    set layout manager

    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
    binding.rvItem.setHasFixedSize(true)
    binding.rvItem.isNestedScrollingEnabled = true
    binding.rvItem.addItemDecoration(itemDecor)
    binding.rvItem.layoutManager = linearLayoutManager
//    adapter

    var adapter = ItemRecyclerAdapter(this, alItem)
    binding.rvItem.adapter = adapter

//  pull to refresh
    binding.pullToRefresh.setOnRefreshListener {
      presenter.callApiForData(isLoadMore = false)
      binding.pullToRefresh.isRefreshing = false
    }

    addScrollListenerToRecyclerView(layout = linearLayoutManager)
  }

  private fun addScrollListenerToRecyclerView(layout: LinearLayoutManager) {

    binding.rvItem.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layout) {
      override fun onLoadMore(page: Int, totalItemsCount: Int) {
        hitApiForData()
      }

    })
  }
  /*
  * api calll
  * */

  private fun hitApiForData() {
    showMoreDataLoading()

    Handler().postDelayed(object : Runnable {
      override fun run() {
        presenter.callApiForData(isLoadMore = true)
      }
    }, 1500)


  }

  private fun showMoreDataLoading() {
    binding.itemProgressBar.exVisible()
  }

  private fun hideMoreDataLoading() {
    binding.itemProgressBar.exGone()
  }
/*
* api response
* */
  override fun getApiDataSuccess(isLoadMore: Boolean, value: ResponseData) {
    hideMoreDataLoading()

    setTitleCustom(value.title)

    when(isLoadMore){
      false -> alItem.clear()
    }

    alItem.addAll(value.rows)
  }

  override fun getApiDataFailure(msg: String) {
    hideMoreDataLoading()
  }
}