package motobeans.architecture.development.interfaces

import com.optcrm.optreporting.app.persistence.db.MasterDB

/**
 * Created by swati on 24/9/2018.
 */

interface DataBaseUtil {
    fun provideDataBaseSource(): MasterDB
}