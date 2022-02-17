package com.example.cse225ca1sharanya

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class Homepage : AppCompatActivity() {
    lateinit var receiver:AeroplaneReceiver
    lateinit var ratingBar: RatingBar
    lateinit var button: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage2)
        val toolbar = findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        ratingBar = findViewById(R.id.ratingBar)
        ratingBar.numStars = 5
        button = findViewById(R.id.buttonCheck)
        textView = findViewById(R.id.textView)
        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                Toast.makeText(
                    this@Homepage, "Stars: " +
                            rating.toInt(), Toast.LENGTH_SHORT
                ).show()
            }
        button.setOnClickListener {
            textView.text = "You have got " + ratingBar.rating.toInt()+ " stars"
        }
        receiver = AeroplaneReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver,it)
        }

    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}