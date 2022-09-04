package com.example.yambprojekt.db

class InMemoryDb : LeaderboardDao {

    private val results = mutableListOf<LeaderboardEntity>()

    override fun insert(result: LeaderboardEntity) {
        results.add(result)
    }

    override fun delete(result: LeaderboardEntity) {
        results.remove(result)
    }

    override fun getAll(): List<LeaderboardEntity> {
        return results
    }
}