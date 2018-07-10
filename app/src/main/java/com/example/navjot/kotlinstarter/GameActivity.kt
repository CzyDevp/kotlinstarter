package com.example.navjot.kotlinstarter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


class GameActivity : AppCompatActivity() {
    private lateinit var gameScore : TextView
    private lateinit var gameTime: TextView
    private lateinit var hitMe: Button
    internal var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameScore = findViewById(R.id.game_score)
        gameTime = findViewById(R.id.time)
        hitMe = findViewById(R.id.hit_me)
        hitMe.setOnClickListener{_->incrementScore()}
    }

    private fun resetGame(){

    }

    private fun startGame(){

    }

    private fun  endGame(){

    }

    private fun incrementScore(){
      score++
        var newScore = "Your score: " + Integer.toString(score)
        gameScore.text = newScore
    }

}
