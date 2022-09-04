package com.example.yambprojekt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = arrayOf(LeaderboardEntity::class),
    exportSchema = false
)
abstract class RoomLeaderboardDb : RoomDatabase() {

    abstract fun getLeaderboardDao(): LeaderboardDao

    companion object{
        private const val NAME = "leaderboard database"
        @Volatile
        private var INSTANCE: RoomLeaderboardDb? = null

        fun getInstance(context: Context): RoomLeaderboardDb {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): RoomLeaderboardDb {
            return Room.databaseBuilder(
                context.applicationContext,
                RoomLeaderboardDb::class.java,
                NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}