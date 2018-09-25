package com.optcrm.optreporting

import android.app.Application
import dagger.Module
import dagger.Provides
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.development.implementation.AppUtilImpl
import motobeans.architecture.development.interfaces.AppUtil
import motobeans.architecture.development.interfaces.DataBaseUtil
import motobeans.architecture.development.interfaces.SharedPreferencesUtil
import javax.inject.Singleton

/**
 * Created by swati on 24/9/2018.
 */
@Module
class AppModule(private val app: ArchitectureApp) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideArchitecture(): ArchitectureApp {
        return app
    }

    @Provides
    @Singleton
    fun provideAppUtil(databaseUtil: DataBaseUtil, sharedPreferencesUtil: SharedPreferencesUtil): AppUtil {
        return AppUtilImpl(databaseUtil = databaseUtil, sharedPreferencesUtil = sharedPreferencesUtil)
    }
}