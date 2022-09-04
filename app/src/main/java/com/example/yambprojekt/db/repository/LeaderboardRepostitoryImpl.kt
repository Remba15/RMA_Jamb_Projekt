package com.example.yambprojekt.db.repository

import com.example.yambprojekt.db.LeaderboardDao
import com.example.yambprojekt.db.LeaderboardEntity

class LeaderboardRepostitoryImpl(private val leaderboardDao: LeaderboardDao):
    LeaderboardRepository {
    override fun insert(result: LeaderboardEntity) = leaderboardDao.insert(result)

    override fun delete(result: LeaderboardEntity) = leaderboardDao.delete(result)

    override fun getAll(): List<LeaderboardEntity> = leaderboardDao.getAll()
}