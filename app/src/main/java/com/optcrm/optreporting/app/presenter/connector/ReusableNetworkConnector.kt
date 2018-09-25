package com.optcrm.optreporting.app.presenter.connector

import motobeans.architecture.constants.ConstantsApi

/**
 * Created by swati on 24/9/2018.
 */
interface ReusableNetworkConnector {
    fun callNetwork(type: ConstantsApi)
}