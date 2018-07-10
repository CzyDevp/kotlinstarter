package com.example.navjot.kotlinstarter
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    private lateinit var gameScore : TextView
    private lateinit var gameTime: TextView
    private lateinit var hitMe: Button
    internal var score = 0
    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal var intialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var timeLeft = 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameScore = findViewById(R.id.game_score)
        gameTime = findViewById(R.id.time)
        hitMe = findViewById(R.id.hit_me)
        hitMe.setOnClickListener{_->incrementScore()}
        resetGame()
    }

    private fun resetGame(){
        score=0
        val initialScore = getString(R.string.score,Integer.toString(score))
        gameScore.text = initialScore
        val initialTimeLeft = getString(R.string.time,Integer.toString(60))
        gameTime.text = initialTimeLeft
        countDownTimer = object : CountDownTimer(intialCountDown,countDownInterval)
        {
            override fun onFinish() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                timeLeft = millisUntilFinished.toInt()/1000
                val timeLeftString = getString(R.string.time,Integer.toString(timeLeft))
                gameTime.text = timeLeftString
            }
        }
        gameStarted = false

    }

    private fun startGame(){
      countDownTimer.start()
        gameStarted= true
    }

    private fun  endGame(){
        Toast.makeText(this,
                        getString(R.string.game_over_message,Integer.toString(score)),
                        Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun incrementScore(){
        if(!gameStarted){
            startGame()
        }
      score++
        //var newScore = "Your score: " + Integer.toString(score)
        var newScore = getString(R.string.score,Integer.toString(score))
        gameScore.text = newScore
    }

}
