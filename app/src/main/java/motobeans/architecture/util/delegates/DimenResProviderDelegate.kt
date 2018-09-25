package motobeans.architecture.util.delegates

import android.app.Activity
import android.support.annotation.DimenRes
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by swati on 24/9/2018.
 */
class DimenResProviderDelegate(@DimenRes private val dimensionRes: Int) : ReadOnlyProperty<Activity, Float> {

    override fun getValue(thisRef: Activity, property: KProperty<*>): Float {
        return thisRef.resources.getDimension(dimensionRes)
    }
}