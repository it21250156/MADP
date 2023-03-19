package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class add_new_chariti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_chariti)


        var buttontwentyone = findViewById<Button>(R.id.button21)
        buttontwentyone.setOnClickListener {
            val intent1 = Intent (this, edu_charity_admin::class.java)
            startActivity(intent1)
        }
    }
}