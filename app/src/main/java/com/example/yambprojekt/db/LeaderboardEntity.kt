package com.example.yambprojekt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard_table")
class LeaderboardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "Name") val mName: String,
    @ColumnInfo(name = "Result") val mResult: String,
    @ColumnInfo(name = "Date") val mDate: String
) {

}