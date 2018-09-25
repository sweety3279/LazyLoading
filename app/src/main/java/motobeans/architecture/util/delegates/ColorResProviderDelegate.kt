package motobeans.architecture.util.delegates

import android.app.Activity
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by swati on 24/9/2018.
 */
class ColorResProviderDelegate(@ColorRes private val colorRes: Int) : ReadOnlyProperty<Activity, Int> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): Int {
        return ContextCompat.getColor(thisRef, colorRes)
    }
}