package com.example.yambprojekt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        }


    }

    private fun openNewGame() {
        val newGameIntent: Intent = Intent(this, GameActivity::class.java)
        startActivity(newGameIntent)
    }

    private fun openInfo(){

    }

    private fun openLeaderboard(){

    }

}