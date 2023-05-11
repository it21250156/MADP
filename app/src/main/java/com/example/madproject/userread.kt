package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class userread : AppCompatActivity() {

    private lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userread)

        // Initialize the description TextView by finding it from the layout
        description = findViewById(R.id.userreads)

        setValuesToViews()
    }

    private fun setValuesToViews() {
        description.text = intent.getStringExtra("userreads")
    }
}
