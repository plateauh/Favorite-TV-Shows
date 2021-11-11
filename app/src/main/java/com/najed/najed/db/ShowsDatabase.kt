package com.najed.najed.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Show::class], version = 1, exportSchema = false)
abstract class ShowsDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: ShowsDatabase? = null

        fun getInstance(context: Context): ShowsDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowsDatabase::class.java,
                    "shows_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
    abstract fun ShowDAO(): ShowDAO
}