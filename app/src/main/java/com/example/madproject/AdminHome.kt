package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AdminHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
    }

    fun adminPosts(view: View){
        val intent = Intent(this, AdminPostListActivity::class.java)
        startActivity(intent)
    }

    fun adminCharity(view: View){
        val intent = Intent(this,edu_charity_admin::class.java)
        startActivity(intent)
    }
}