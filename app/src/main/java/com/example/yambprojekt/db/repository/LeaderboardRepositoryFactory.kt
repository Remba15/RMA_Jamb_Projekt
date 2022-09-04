package com.example.yambprojekt.db.repository

import com.example.yambprojekt.Yamb
import com.example.yambprojekt.db.RoomLeaderboardDb

object LeaderboardRepositoryFactory {

    val roomDb = RoomLeaderboardDb.getInstance(Yamb.application)
    val leaderboardRepository: LeaderboardRepository = LeaderboardRepostitoryImpl(roomDb.getLeaderboardDao())
}