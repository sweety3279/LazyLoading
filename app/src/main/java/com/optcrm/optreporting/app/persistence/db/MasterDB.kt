package com.optcrm.optreporting.app.persistence.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.optcrm.optreporting.app.persistence.converters.ConverterArrayList
import com.optcrm.optreporting.app.persistence.converters.Converters
import com.optcrm.optreporting.app.persistence.dao.TempDao
import com.optcrm.optreporting.app.persistence.model.TempModel
import kotlinx.coroutines.experimental.launch

/**
 * The Room database.
 */
@Database(
    entities = [TempModel::class],
    version = 1)
@TypeConverters(value = [Converters::class, ConverterArrayList::class])
abstract class MasterDB : RoomDatabase() {

  companion object {
    /** The only instance */
    @Volatile
    private var INSTANCE: MasterDB? = null

    /**
     * Gets the singleton instance of MasterDB.
     *
     * @param context The context.
     * @return The singleton instance of MasterDB.
     */
    fun getInstance(context: Context): MasterDB =
        INSTANCE ?: synchronized(this) {
          INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

    /**
     * Switches the internal implementation with an empty in-memory database.
     *
     * @param context The context.
     */
    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            MasterDB::class.java, "MasterOPT.db")
            .build()
  }

  @SuppressWarnings("WeakerAccess")
  abstract fun tempDao(): TempDao

  fun reconfigDataFromDBASync(){

  }
  fun deleteAllTableDataFromDBAsycn() {
    launch {

    }
  }
}