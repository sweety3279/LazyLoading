package motobeans.architecture.util

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.math.BigDecimal
import java.util.regex.Pattern

/**
 * Created by swati on 24/9/2018.
 */

fun String.exShowToast(context: Context?) = Toast.makeText(context, this, Toast.LENGTH_LONG).show()

fun String?.exIsNotEmptyOrNullOrBlank() = !this.isNullOrBlank() && !this.isNullOrEmpty()




fun String.exToDouble(): Double {
  try {
    return this.toDouble()
  } catch (e: Exception) {
    return 0.0
  }
}


fun View.exGone() {
  arrayOf(this).exGone()
}

fun View.exVisible() {
  arrayOf(this).exVisible()
}



fun <T : View> Array<T>.exGone() {
  for (view in this) {
    view.visibility = View.GONE
  }
}

fun <T : View> Array<T>.exVisible() {
  for (view in this) {
    view.visibility = View.VISIBLE
  }
}


