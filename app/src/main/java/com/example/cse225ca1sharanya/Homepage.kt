package com.example.cse225ca1sharanya

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintSet

class Homepage : AppCompatActivity() {
    lateinit var receiver:AeroplaneReceiver
    lateinit var ratingBar: RatingBar
    lateinit var button: Button
    lateinit var textView: TextView
    lateinit var buttonforcus: Button
    lateinit var sw_wrapper : ScrollView
    lateinit var progressBar: ProgressBar
//    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage2)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        ratingBar = findViewById(R.id.ratingBar)
        ratingBar.numStars = 5
        button = findViewById(R.id.buttonCheck)
        textView = findViewById(R.id.rateText)
        sw_wrapper =  findViewById(R.id.scroll)
    progressBar = findViewById(R.id.progressBar)
        var parentLayout=findViewById<LinearLayout>(R.id.parentLayout)

        sw_wrapper.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val  scrollLen = sw_wrapper.getChildAt(0).height - sw_wrapper.height
            progressBar.apply {
                max = scrollLen
                progress = i2
            }
        }



        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->

            }
        button.setOnClickListener {
            textView.text = "You have got " + ratingBar.rating.toInt()+ " stars"
            Toast(this).apply {

                duration = Toast.LENGTH_LONG
                setGravity(Gravity.TOP,0,250)
                view = layoutInflater.inflate(R.layout.custom_toast,parentLayout)
            }.show()
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