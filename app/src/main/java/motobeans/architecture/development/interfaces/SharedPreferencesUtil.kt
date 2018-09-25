package motobeans.architecture.development.interfaces

import motobeans.architecture.retrofit.response.Response.ResponseSample

/**
 * Created by swati on 24/9/2018.
 */
interface SharedPreferencesUtil {
    fun saveLoginData(response: ResponseSample?): Boolean
    fun getLoginData(): ResponseSample?
    fun isLogin(): Boolean
    fun getUserId(): String?
    fun getUserName(): String?

    // Clear All saved data from Shared Presence
    fun clearAll()
}