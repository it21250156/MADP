package com.example.madproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class selected_food_charity : AppCompatActivity() {

    private lateinit var foodcharityId: TextView
    private lateinit var foodcharityname: TextView
    private lateinit var foodcharityaddress: TextView
    private lateinit var foodcharitycontact: TextView
    private lateinit var foodcharityemail: TextView
    private lateinit var foodcharitydescription: TextView
    private lateinit var donateBtn:Button
    private lateinit var backToCharitiCate:Button

    override fun onCreate(

        savedInstanceState: Bundle?
    ){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_food_charity)

//        donateBtn.setOnClickListener {
//            val fragment = CardDetails()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.fl_nav, fragment)?.commit()
//        }
//
//        backToCharitiCate.setOnClickListener {
//            val fragment = Educational_charity_list()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.fl_nav, fragment)?.commit()
//        }

        initView()

        setValuesToViews()
    }

    private fun initView() {
       foodcharityId = findViewById(R.id.foodcharityId)
        foodcharityname = findViewById(R.id.foodcharityName)
        foodcharityaddress = findViewById(R.id.foodcharitiaddress)
        foodcharitycontact = findViewById(R.id.foodcharityContact)
        foodcharityemail = findViewById(R.id.foodcharityEmail)
        foodcharitydescription = findViewById(R.id.foodcharityDescription)



//        val args = arguments
//        if (args != null) {
//            charityId.text = args.getString("Charity Id")
//            charityname.text = args.getString("Charity Name")
//            charityaddress.text = args.getString("Charity Address")
//            charitycontact.text = args.getString("Charity Contact")
//            charityemail.text = args.getString("Charity Email")
//            charitydescription.text = args.getString("Charity Description")
//        }
    }

    private fun setValuesToViews() {
        foodcharityId.text = intent.getStringExtra("foodcharityId")
        foodcharityname.text = intent.getStringExtra("foodcharityName")
        foodcharityaddress.text = intent.getStringExtra("foodcharityAddress")
        foodcharitycontact.text = intent.getStringExtra("foodcharityContact")
        foodcharityemail.text = intent.getStringExtra("foodcharityEmail")
        foodcharitydescription.text = intent.getStringExtra("foodcharityDescription")
    }

}
