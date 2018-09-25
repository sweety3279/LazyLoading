package motobeans.architecture.development.implementation

import android.content.Context
import com.optcrm.optreporting.app.persistence.db.MasterDB
import motobeans.architecture.development.interfaces.DataBaseUtil

/**
 * Created by swati on 24/9/2018.
 */

class DaaBaseUtilImpl(private val context: Context) : DataBaseUtil {

    override fun provideDataBaseSource(): MasterDB {
        return MasterDB.getInstance(context)
    }
}