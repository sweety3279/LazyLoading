package motobeans.architecture.development.modules

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import motobeans.architecture.constants.Constants
import motobeans.architecture.constants.Constants.Injection.ENDPOINT_V1
import motobeans.architecture.constants.Constants.Injection.GSON_V1
import motobeans.architecture.constants.Constants.Injection.INTERCEPTOR_HEADER_V1
import motobeans.architecture.constants.Constants.Injection.INTERCEPTOR_LOGGING_V1
import motobeans.architecture.constants.Constants.Injection.INTERCEPTOR_RESPONSE_V1
import motobeans.architecture.constants.Constants.Injection.OKHHTP_CACHE_V1
import motobeans.architecture.constants.Constants.Injection.OKHHTP_CLIENT_V1
import motobeans.architecture.constants.Constants.Injection.RETROFIT_V1
import motobeans.architecture.development.implementation.ApiEndPointImpl
import motobeans.architecture.development.implementation.ApiProjectImpl
import motobeans.architecture.development.interfaces.ApiProject
import motobeans.architecture.development.interfaces.EndPoint
import motobeans.architecture.development.interfaces.SharedPreferencesUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by swati on 24/9/2018.
 */

@Module
class NetworkModule {

  @Provides
  @Singleton
  @Named(GSON_V1)
  internal fun provideGsonV1(): Gson {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
  }

  @Provides
  @Singleton
  @Named(OKHHTP_CACHE_V1)
  internal fun provideOkHttpCacheV1(application: Application): Cache {
    // Install an HTTP cache in the application cache directory.
    val cacheDir = File(application.cacheDir, "http")

    return Cache(cacheDir, DISK_CACHE_SIZE.toLong())
  }

  @Provides
  @Singleton
  @Named(OKHHTP_CLIENT_V1)
  internal fun provideOkHttpClientV1(@Named(OKHHTP_CACHE_V1) cache: Cache,
      @Named(INTERCEPTOR_LOGGING_V1) logging: HttpLoggingInterceptor,
      @Named(INTERCEPTOR_HEADER_V1) headerInterceptor: Interceptor,
      @Named(INTERCEPTOR_RESPONSE_V1) globalResponseInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    //builder.networkInterceptors().add(cachingControlInterceptor);

    return builder
        .addInterceptor(headerInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .addInterceptor(globalResponseInterceptor)
        .cache(cache)
        .build()
  }

  @Provides
  @Singleton
  @Named(INTERCEPTOR_RESPONSE_V1)
  internal fun provideGlobalApiResponseInterceptorV1(application: Application,
      sharedPreferencesUtil: SharedPreferencesUtil): Interceptor {
    return Interceptor { chain ->

      val request = chain.request()
      val response = chain.proceed(request)
      //val responseTemp = chain.proceed(request)
      var responseOriginal: Response? = null

      try {
        val responseBody = response.body()?.string()
        val jsonObject = JSONObject(responseBody)
        jsonObject?.let {
          val isStatusCodeNotValid = jsonObject.optInt("status", 0)
          if (isStatusCodeNotValid != 0) {
            if (isStatusCodeNotValid == 1001) {
              //logout(application, sharedPreferencesUtil)
            }
          }
        }

        responseOriginal = response.newBuilder()
            .body(ResponseBody.create(response.body()?.contentType(), responseBody))
            .build()
/*
        if (response.code() == 403) {
          logout(application, sharedPreferencesUtil)
        }*/
      } catch (e: Exception) {
        e.printStackTrace()
      }

      responseOriginal
    }
  }

  private fun logout(application: Application, sharedPreferencesUtil: SharedPreferencesUtil) {
    sharedPreferencesUtil.clearAll()
  }


  @Provides
  @Singleton
  @Named(INTERCEPTOR_HEADER_V1)
  internal fun provideRetrofitHeaderV1(sharedPreferencesUtil: SharedPreferencesUtil,
      application: Application): Interceptor {
    return Interceptor { chain ->

      val original = chain.request()

      val builder = original.newBuilder()
      builder.header("Authorization", "basic ${sharedPreferencesUtil.getUserId()}")
      builder.header("Content-Type", "application/json").method(original.method(), original.body())
      builder.header("platform", "Android")

      val request = builder.build()

      chain.proceed(request)
    }
  }

  @Provides
  @Singleton
  @Named(INTERCEPTOR_LOGGING_V1)
  internal fun provideLoggingInterceptorV1(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
  }

  @Provides
  @Named(ENDPOINT_V1)
  internal fun provideEndPointV1(@Named(Constants.Injection.API_CURRENT_URL)
  URL: String): EndPoint {
    return ApiEndPointImpl().setEndPoint(URL)
  }

  @Provides
  @Singleton
  @Named(RETROFIT_V1)
  internal fun provideRetrofitV1(@Named(GSON_V1) gson: Gson,
      @Named(OKHHTP_CLIENT_V1) okHttpClient: OkHttpClient,
      @Named(ENDPOINT_V1) endPoint: EndPoint): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(endPoint.url!!)
        .client(okHttpClient)
        .build()
  }

  @Provides
  @Singleton
  internal fun provideRetrofitApiV1(@Named(RETROFIT_V1) retrofit: Retrofit): ApiProject {
    return ApiProjectImpl(retrofit)
  }

  companion object {

    internal val DISK_CACHE_SIZE = 1024 * 1024
  }

}
