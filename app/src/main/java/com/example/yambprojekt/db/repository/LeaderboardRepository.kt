package com.example.yambprojekt.db.repository

import com.example.yambprojekt.db.LeaderboardEntity

interface LeaderboardRepository {
    fun insert(result: LeaderboardEntity)
    fun delete(result: LeaderboardEntity)
    fun getAll(): List<LeaderboardEntity>
}