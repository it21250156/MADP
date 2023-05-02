package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.madproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val logintxt: TextView = findViewById(R.id.tv_login_now)

        logintxt.setOnClickListener {
            val intent = Intent(this, LoginActivity2::class.java)
            startActivity(intent)
        }

        val registerBtn: Button = findViewById(R.id.btn_signup_register)

        registerBtn.setOnClickListener {
            performSignUp()
        }

        //get email and password from the user

    }

    private fun performSignUp() {

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val name = findViewById<EditText>(R.id.edtText_signup_name)
        val email = findViewById<EditText>(R.id.edtText_signup_email)
        val mobileNo = findViewById<EditText>(R.id.edtText_signup_mobile)
        val password = findViewById<EditText>(R.id.edtText_signup_password)
        val confirmPassword = findViewById<EditText>(R.id.edtText_signup_confirmpassword)

        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputName = name.text.toString()
        val inputEmail = email.text.toString()
        val inputMobileNum = mobileNo.text.toString()
        val inputPassword = password.text.toString()
        val inputConfirmPassword = confirmPassword.text.toString()

        if (inputPassword == inputConfirmPassword) {
            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
//                        database = FirebaseDatabase.getInstance().getReference("Users")
//                        val User = User(inputName,inputEmail,inputMobileNum,inputPassword)
//                        database.child(inputName).setValue(User).addOnSuccessListener {
//                            name.text.clear()
//                            email.text.clear()
//                            mobileNo.text.clear()
//                            password.text.clear()
//                            confirmPassword.text.clear()

                        // Sign in success, move to home
                        val intent = Intent(this, CustomerMain::class.java)
                        startActivity(intent)

                        Toast.makeText(
                            baseContext,
                            "Success.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                    }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error occurred ${it.localizedMessage}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                } else {
                Toast.makeText(this, "Please make sure your passwords match.", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
