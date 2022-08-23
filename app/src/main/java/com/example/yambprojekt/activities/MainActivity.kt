package com.example.yambprojekt.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.yambprojekt.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNewGame = findViewById<Button>(R.id.btn_newGame)
        btnNewGame.setOnClickListener {
            openNewGame()
        }

        val btnExit = findViewById<Button>(R.id.btn_exit)
        btnExit.setOnClickListener{
            finishAffinity()
        }

    }


    private fun openNewGame() {
        val newGameIntent: Intent = Intent(this, GameActivity::class.java)
        startActivity(newGameIntent)
    }


}