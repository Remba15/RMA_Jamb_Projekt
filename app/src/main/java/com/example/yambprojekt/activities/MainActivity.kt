package com.example.yambprojekt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.yambprojekt.R
import com.example.yambprojekt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.btnNewGame.setOnClickListener { openNewGame() }
            it.btnExit.setOnClickListener { finishAffinity() }
            it.btnInfo.setOnClickListener {openInfo() }
            it.btnLeaderboard.setOnClickListener { openLeaderboard() }
        }

    }

    private fun openNewGame() {
        val mNewGameIntent = Intent(this, GameActivity::class.java)
        startActivity(mNewGameIntent)
    }

    private fun openInfo(){
        var dialog = InfoDialogFragment()
        dialog.show(supportFragmentManager, "InfoDialog")
    }

    private fun openLeaderboard(){
        val mNewLeaderboardIntent = Intent(this, LeaderboardActivity::class.java)
        startActivity(mNewLeaderboardIntent)
    }

}