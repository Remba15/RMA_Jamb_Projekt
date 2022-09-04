package com.example.yambprojekt.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yambprojekt.adapters.LeaderboardAdapter
import com.example.yambprojekt.databinding.ActivityLeaderboardBinding
import com.example.yambprojekt.db.LeaderboardEntity
import com.example.yambprojekt.db.repository.LeaderboardRepository
import com.example.yambprojekt.db.repository.LeaderboardRepositoryFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var adapter : LeaderboardAdapter
    private val leaderboardRepository = LeaderboardRepositoryFactory.leaderboardRepository
    var index: Int = 0

    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLeaderboardBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        adapter = LeaderboardAdapter()
        binding.rvLeaderboard.adapter = adapter
        binding.rvLeaderboard.layoutManager = LinearLayoutManager(this)
        binding.rvLeaderboard.hasFixedSize()

        binding.btnAddLbItem.setOnClickListener { addLeaderboardItem(binding.etName.text.toString(),
            binding.etResult.text.toString(), binding.etDate.text.toString())
        }

        binding.btnDeleteLbItem.setOnClickListener {
            deleteAll()
        }

        binding.btnSaveAll.setOnClickListener {

        }

        dataStore = createDataStore(name = "leaderboard")
    }

    private suspend fun save(){
        dataStore.edit {

        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setLeaderboard(leaderboardRepository.getAll())
    }

    private fun addLeaderboardItem(name: String, result: String, date: String) {
        adapter.addResult(LeaderboardEntity(index, name, result, date))
        index++
    }

    private fun deleteAll() {
        adapter.deleteAll()
    }
}