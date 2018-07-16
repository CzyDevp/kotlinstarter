package com.example.navjot.kotlinstarter
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
class GameActivity : AppCompatActivity() {
    internal var score = 0
    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var timeLeft = 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        hit_me.setOnClickListener{v->
            val animation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            v.startAnimation(animation)
            incrementScore()
        }
        resetGame()
    }

    private fun resetGame(){
        score=0
        val initialScore = getString(R.string.score,Integer.toString(score))
        game_score.text = initialScore
        val initialTimeLeft = getString(R.string.time,Integer.toString(60))
        time.text = initialTimeLeft
        countDownTimer = object : CountDownTimer(initialCountDown,countDownInterval)
        {
            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt()/1000
                val timeLeftString = getString(R.string.time,Integer.toString(timeLeft))
                time.text = timeLeftString
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
        game_score.text = newScore
    }

}
