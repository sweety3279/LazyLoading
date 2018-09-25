package motobeans.architecture.development.interfaces

import com.optcrm.optreporting.databinding.MainActivityBinding

/**
 * Created by swati on 24/9/2018.
 */

interface FormValidation {
    fun validateTemp(binding: MainActivityBinding): Boolean
}