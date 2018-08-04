package com.example.navjot.kotlinstarter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_counter.*

class Counter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
    }
    fun countMe(view: View){
        var count:Int = Integer.parseInt(textView.text.toString())
        count++
        textView.text=count.toString()

    }

    fun toastMe(view: View){
        val toast = Toast.makeText(this,"Hello Toast!!",Toast.LENGTH_LONG)
        toast.show()
    }

    fun randomMe(view: View){
        val randomIntent = Intent(this,Random::class.java)
        val getCurrentCount = Integer.parseInt(textView.text.toString())
        randomIntent.putExtra(Random.TOTAL_COUNT,getCurrentCount)
        startActivity(randomIntent)
    }
}