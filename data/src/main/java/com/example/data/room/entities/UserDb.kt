package com.example.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("uid")
    var uid : String,
    @ColumnInfo("name_full")
    var fullName : String?,
    @ColumnInfo("email")
    var email : String?,
    @ColumnInfo("mobile_phone")
    var mobilePhone : String?
)