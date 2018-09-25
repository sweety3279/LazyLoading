package com.optcrm.optreporting.app.presenter.connector

import motobeans.architecture.retrofit.response.Response.ResponseSample

/**
 * Created by swati on 24/9/2018.
 */
interface TempSyncConnector {
    interface ViewOptLocalToServer {
        fun getSyncLocalToServerSuccess(value: List<ResponseSample>)
        fun getSyncLocalToServerFailure(msg: String)
    }

    interface ViewOptServerToLocal {
        fun getSyncServerToLocalSuccess(value: List<ResponseSample>)
        fun getSyncServerToLocalFailure(msg: String)
    }

    interface PresenterOpt : ReusableNetworkConnector
}