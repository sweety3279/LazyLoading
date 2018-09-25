package motobeans.architecture.customAppComponents.dialog

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by swati on 24/9/2018.
 */
abstract class CustomDialogFragment<T : ViewDataBinding> : DialogFragment() {

    abstract fun getView(binding: T)
    abstract fun setView(): Int
    abstract fun init()

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        initBinding(inflater, container)

        return rootView()
    }

    fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {

        val binding = DataBindingUtil.inflate<T>(inflater, setView(), container, false)
        rootView = binding.root

        getView(binding)
        init()
    }

    fun rootView(): View? {
        return rootView
    }

}