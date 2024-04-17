package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.room.entities.UserDb

@Dao
interface UserAuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserDb)

    @Query("SELECT * FROM user")
    fun isRegistrationUser() : List<UserDb>

    @Update
    suspend fun update(user: UserDb)

}