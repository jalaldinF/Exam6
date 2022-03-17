package com.example.exam6modul.database

import android.app.Application
import android.util.Log
import com.example.exam6modul.manager.RoomManager
import com.example.exam6modul.model.Card

class UserRepository(application: Application) {

    val TAG:String = UserRepository::class.java.simpleName
    private val db = RoomManager.getDatabase(application)
    private val userDao: UserDao = db!!.UserDao()


    fun getUsers():List<UserDB>{
        Log.d("TAG","${userDao.getUsers()}")
        return userDao.getUsers()
    }
    fun saveUser(userDB: UserDB){
        Log.d("TAG","Saved")
        userDao.saveUser(userDB)
    }
    fun deleteUsers(){
        Log.d("TAG","Cleared")
        userDao.deleteAllUsers()
    }

}