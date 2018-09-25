package motobeans.architecture.customAppComponents.interfaces

import android.app.Activity
import android.content.Context

/**
 * Created by swati on 24/9/2018.
 */
interface CustomAppActivity {
    fun getActivity(): Activity
    fun getContext(): Context
}