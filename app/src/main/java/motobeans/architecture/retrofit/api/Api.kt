package motobeans.architecture.retrofit.api

import io.reactivex.Observable
import motobeans.architecture.retrofit.response.Response
import retrofit2.http.GET

/**
 * Created by swati on 24/9/2018.
 */

interface Api {
  @GET("facts.json")
  fun getData(): Observable<Response.ResponseData>
}