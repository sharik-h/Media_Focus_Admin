package com.mediafocusadmin.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mediafocusadmin.model.Expense

@Database(entities = [Expense::class], version = 4, exportSchema = false)
abstract class RoomDatabases: RoomDatabase() {
    abstract val dao: ExpenseDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDatabases? = null

        fun getDatabase(context: Context): RoomDatabases {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabases::class.java,
                        "PaymentRoom"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}