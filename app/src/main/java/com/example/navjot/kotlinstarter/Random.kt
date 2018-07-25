package com.example.navjot.kotlinstarter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_random.*
import java.util.Random
class Random:AppCompatActivity() {
    companion object {
        const val TOTAL_COUNT = "total-count"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        showRandom()
    }

    private fun showRandom(){
        val count = intent.getIntExtra(TOTAL_COUNT,0)
        val randm =  Random()
        var randomInt = 0
        if(count>0) randomInt = randm.nextInt(count+1)
        random.text= Integer.toString(randomInt)
        random_heading.text = getString(R.string.heading,count)
    }
}