package com.optcrm.optreporting.app.presenter.connector

/**
 * Created by swati on 24/9/2018.
 */
interface ReusableView {
    fun showToast(msg: String)
    fun showProgressDialog()
    fun hideProgressDialog()
}