package com.example.exam6modul.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exam6modul.database.UserDB
import com.example.exam6modul.database.UserDao


@Database(entities = [UserDB::class], version = 1)
abstract class RoomManager:RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object{

        private var INSTACNCE:RoomManager? = null
        fun getDatabase(context: Context): RoomManager? {
            if (INSTACNCE == null){
                synchronized(RoomManager::class.java){
                    INSTACNCE = Room.databaseBuilder(context,RoomManager::class.java,"user.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTACNCE
        }

    }

}