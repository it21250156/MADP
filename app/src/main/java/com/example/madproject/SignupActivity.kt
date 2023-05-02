package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val logintxt: TextView = findViewById(R.id.tv_login_now)

        logintxt.setOnClickListener {
            val intent = Intent(this, LoginActivity2::class.java)
            startActivity(intent)
        }
    }
}