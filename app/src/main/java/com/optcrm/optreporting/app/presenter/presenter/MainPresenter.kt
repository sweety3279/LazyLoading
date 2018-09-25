package com.optcrm.optreporting.app.presenter.presenter

import com.optcrm.optreporting.app.presenter.connector.MainConnector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.constants.ConstantsApi
import motobeans.architecture.constants.ConstantsApi.CALL_TEMP
import motobeans.architecture.development.interfaces.ApiProject
import motobeans.architecture.development.interfaces.SharedPreferencesUtil
import motobeans.architecture.retrofit.response.Response
import javax.inject.Inject

/**
 * Created by swati on 24/9/2018.
 */
class MainPresenter(private val viewOpt: MainConnector.ViewOpt) :  MainConnector.PresenterOpt {
    @Inject
    lateinit var apiProject: ApiProject
    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    init {
        ArchitectureApp.instance.component.inject(this)
    }

    override fun callNetwork(type: ConstantsApi) {
        when (type) {
            CALL_TEMP -> callTempApi()
        }
    }
    private fun callTempApi() {
        val requestApi = apiProject.api.getData()

        requestApi
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _ -> viewOpt.showProgressDialog() }
            .doFinally { viewOpt.hideProgressDialog() }
            .subscribe({ resposne -> onNextForgotPassword(resposne) },
                { e -> viewOpt.getObjectFailure(e?.message ?: "") })
    }


    private fun onNextForgotPassword(response: Response.ResponseData) {
        val msg = "Data does not exist"

    }
}