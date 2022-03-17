package com.example.exam6modul.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserDB (

    @PrimaryKey
    val id:String,

    val card_number:Int,
    val owner_name:String,
    val end_date:String,

)