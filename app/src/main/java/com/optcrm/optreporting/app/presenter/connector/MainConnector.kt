package com.optcrm.optreporting.app.presenter.connector

import motobeans.architecture.retrofit.request.Requests.RequestSample
import motobeans.architecture.retrofit.response.Response.ResponseSample

/**
 * Created by swati on 24/9/2018.
 */
interface MainConnector {

    interface ViewOpt: ReusableView {
        fun getObjectSuccess(value: ResponseSample)
        fun getObjectFailure(msg: String)

    }

    interface PresenterOpt: ReusableNetworkConnector

}