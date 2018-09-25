package motobeans.architecture.application

import android.app.Application
import android.content.Context
import com.optcrm.optreporting.AppModule
import motobeans.architecture.development.components.ApplicationComponent
import motobeans.architecture.development.components.DaggerApplicationComponent
import motobeans.architecture.development.modules.NetworkModule
import motobeans.architecture.development.modules.PrimitivesModule
import motobeans.architecture.development.modules.UtilityModule
import motobeans.architecture.util.FontsOverride


/**
 * Created by swati on 24/9/2018.
 */
class ArchitectureApp : Application() {

    companion object {
        lateinit var instance: ArchitectureApp
            private set
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .utilityModule(UtilityModule())
            .primitivesModule(PrimitivesModule())
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/toboto_regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/toboto_regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/toboto_regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/toboto_regular.ttf");
        instance = this
        component.inject(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}