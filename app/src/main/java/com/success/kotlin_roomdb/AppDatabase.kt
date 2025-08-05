package com.success.kotlin_roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    MenuItem::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase (){

    abstract fun MenuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        // Database building
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cafeposdb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}