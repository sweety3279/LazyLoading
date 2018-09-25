package motobeans.architecture.util.delegates

import android.app.Activity
import android.support.annotation.StringRes
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by swati on 24/9/2018.
 */
class StringResProviderDelegate(@StringRes private val stringRes: Int) : ReadOnlyProperty<Activity, String> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): String {
        return thisRef.resources.getString(stringRes)
    }
}