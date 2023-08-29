package com.mediafocusadmin.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mediafocusadmin.model.Payment

@Database(entities = [Payment::class], version = 5, exportSchema = false)
abstract class PayRoomDatabase: RoomDatabase() {
    abstract val dao: PaymentDao

    companion object {

        @Volatile
        private var INSTANCE: PayRoomDatabase? = null

        fun getDatabase(context: Context): PayRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PayRoomDatabase::class.java,
                        "ExpenseRoom"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}