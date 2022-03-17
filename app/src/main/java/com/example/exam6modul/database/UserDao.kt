package com.example.exam6modul.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exam6modul.model.Card

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userDB: UserDB)

    @Query("SELECT * FROM users")
    fun getUsers():List<UserDB>

    @Query("DELETE FROM users")
    fun deleteAllUsers()

}