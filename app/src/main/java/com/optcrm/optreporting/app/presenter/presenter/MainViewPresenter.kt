package com.optcrm.optreporting.app.presenter.presenter

import com.optcrm.optreporting.app.presenter.connector.MainViewConnector.PresenterOpt
import com.optcrm.optreporting.app.presenter.connector.MainViewConnector.ViewOpt
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
class MainViewPresenter(private val viewOpt: ViewOpt) : PresenterOpt {
    @Inject
    lateinit var apiProject: ApiProject
    @Inject
    lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    init {
        ArchitectureApp.instance.component.inject(this)
    }

    override fun callNetwork(type: ConstantsApi) {
        when (type) {
            CALL_TEMP -> callApiForData(isLoadMore = false)
        }
    }

    override fun callApiForData(isLoadMore: Boolean) {
        val requestApi = apiProject.api.getData()

        requestApi
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _ ->
                //viewOpt.showProgressDialog()
            }
            .doFinally {
                //viewOpt.hideProgressDialog()
            }
            .subscribe({ resposne -> onNextForgotPassword(isLoadMore = isLoadMore, response = resposne) },
                { e -> viewOpt.getApiDataFailure(e?.message ?: "") })
    }


    private fun onNextForgotPassword(isLoadMore: Boolean, response: Response.ResponseData) {
        viewOpt.getApiDataSuccess(isLoadMore, response)

    }
}