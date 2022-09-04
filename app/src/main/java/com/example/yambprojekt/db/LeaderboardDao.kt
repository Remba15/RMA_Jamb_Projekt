package com.example.yambprojekt.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeaderboardDao {

    @Insert
    fun insert(result: LeaderboardEntity)

    @Delete
    fun delete(result: LeaderboardEntity)

    @Query("SELECT * FROM leaderboard_table")
    fun getAll():List<LeaderboardEntity>

}