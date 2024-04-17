package com.example.data.room.app_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.dao.UserAuthDao
import com.example.data.room.entities.UserDb

@Database(
    entities = [UserDb::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userAuthDao(): UserAuthDao
}