package motobeans.architecture.development.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import motobeans.architecture.development.implementation.DaaBaseUtilImpl
import motobeans.architecture.development.implementation.FormValidationImpl
import motobeans.architecture.development.implementation.SharedPreferencesutilImpl
import motobeans.architecture.development.interfaces.DataBaseUtil
import motobeans.architecture.development.interfaces.FormValidation
import motobeans.architecture.development.interfaces.SharedPreferencesUtil
import javax.inject.Singleton

/**
 * Created by swati on 24/9/2018.
 */
@Module
class UtilityModule {
    @Provides
    @Singleton
    fun provideSharePreference(app: Application): SharedPreferencesUtil {
        return SharedPreferencesutilImpl(app)
    }

    @Provides
    @Singleton
    internal fun provideFormvalidation(application: Application): FormValidation {
        return FormValidationImpl(application)
    }

    @Provides
    @Singleton
    internal fun provideDataBase(application: Application): DataBaseUtil {
        return DaaBaseUtilImpl(application)
    }
}