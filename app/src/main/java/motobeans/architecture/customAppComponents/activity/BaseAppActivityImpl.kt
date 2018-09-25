package motobeans.architecture.customAppComponents.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.afollestad.assent.AssentActivity
import motobeans.architecture.customAppComponents.interfaces.CustomAppActivity

/**
 * Created by swati on 24/9/2018.
 */
open class BaseAppActivityImpl : AssentActivity(), CustomAppActivity {

    private var activity: Activity = this
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = this
        context = this

    }

    override fun getActivity(): Activity {
        return activity
    }

    override fun getContext(): Context {
        return context
    }
}