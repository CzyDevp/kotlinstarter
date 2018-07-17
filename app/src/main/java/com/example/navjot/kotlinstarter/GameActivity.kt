package com.example.navjot.kotlinstarter
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*

/**
 * @author NavSingh
 */
class GameActivity : AppCompatActivity() {
    internal var score = 0
    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var timeLeft = 60
    private val tag = GameActivity::class.simpleName
    internal var timeLeftOnTimer: Long =6000
    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY ="TIME_LEFT_KEY"
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"onCreate called. Score is: $score")
        setContentView(R.layout.activity_game)
        hit_me.setOnClickListener{v->
            val animation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            v.startAnimation(animation)
            incrementScore()
        }
        if(savedInstanceState!=null){
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        }
        else {
            resetGame()
        }
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SCORE_KEY,score)
        outState?.putLong(TIME_LEFT_KEY,timeLeftOnTimer)
        countDownTimer.cancel()
        Log.d(tag,"onSavedInstance saving score: $score")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"onDestroy called")
    }
    private fun restoreGame(){
        game_score.text =  getString(R.string.score,score.toString())
        val restoredTime = timeLeftOnTimer/1000
        time.text = getString(R.string.time,restoredTime.toString())
        countDownTimer = object: CountDownTimer(timeLeftOnTimer,countDownInterval){
            override fun onFinish() {
                    endGame()
            }
            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                var timeLeft = millisUntilFinished/1000
                time.text = getString(R.string.time,timeLeft.toString())
            }

        }
        countDownTimer.start()
        gameStarted = true

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
                timeLeftOnTimer = millisUntilFinished
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
        var newScore = getString(R.string.score,Integer.toString(score))
        game_score.text = newScore
    }
}
