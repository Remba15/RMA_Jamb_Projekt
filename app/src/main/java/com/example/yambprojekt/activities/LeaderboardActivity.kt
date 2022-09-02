package com.example.yambprojekt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yambprojekt.R
import com.example.yambprojekt.databinding.ActivityLeaderboardBinding

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLeaderboardBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.btnAddLbItem.setOnClickListener { addLeaderboardItem() }

        }
    }

    private fun addLeaderboardItem() {
        TODO("Not yet implemented")
    }
}