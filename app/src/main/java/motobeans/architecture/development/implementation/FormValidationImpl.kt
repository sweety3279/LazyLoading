package motobeans.architecture.development.implementation

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.widget.EditText
import com.optcrm.optreporting.databinding.MainActivityBinding
import motobeans.architecture.development.interfaces.FormValidation
import motobeans.architecture.util.exIsNotEmptyOrNullOrBlank

/**
 * Created by swati on 24/9/2018.
 */

class FormValidationImpl(private val context: Context) : FormValidation {

    override fun validateTemp(binding: MainActivityBinding): Boolean {
        var errorCount = 0

        return isValidForm(errorCount)
    }

    private fun checkAndSetError(etToCheck: EditText, tilToSet: TextInputLayout,
        errorText: String): Int {
        var errorCount = 0
        val valToCheck = etToCheck.text.toString().trim { it <= ' ' }
        if (valToCheck.exIsNotEmptyOrNullOrBlank()) {
            tilToSet.isErrorEnabled = false
        } else {
            errorCount++
            tilToSet.isErrorEnabled = true
            tilToSet.error = errorText
        }

        return errorCount
    }


    private fun isValidForm(errorCount: Int): Boolean {
        return errorCount <= 0
    }

    private fun isPinValid(pin: String): Boolean {
        if (pin.length == 4 && pin.exIsNotEmptyOrNullOrBlank())
            return true
        return false
    }
}