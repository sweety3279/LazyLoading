package motobeans.architecture.development.implementation

import motobeans.architecture.development.interfaces.AppUtil
import motobeans.architecture.development.interfaces.DataBaseUtil
import motobeans.architecture.development.interfaces.SharedPreferencesUtil

/**
 * Created by swati on 24/9/2018.
 */

class AppUtilImpl(val databaseUtil: DataBaseUtil, val sharedPreferencesUtil: SharedPreferencesUtil) : AppUtil {
  override fun resetApp() {
    resetAppDatabase()
    resetAppSharedPreference()
  }

  override fun reconfigureApp() {
    reconfigureAppDatabase()
  }

  fun reconfigureAppDatabase() {
    databaseUtil.provideDataBaseSource().reconfigDataFromDBASync()
  }


  fun resetAppDatabase() {
    databaseUtil.provideDataBaseSource().deleteAllTableDataFromDBAsycn()
  }

  fun resetAppSharedPreference() {
    sharedPreferencesUtil.clearAll()
  }
}