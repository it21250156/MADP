package com.example.madproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class selected_helth_charity : AppCompatActivity() {

    private lateinit var helthcharityId: TextView
    private lateinit var helthcharityname: TextView
    private lateinit var helthcharityaddress: TextView
    private lateinit var helthcharitycontact: TextView
    private lateinit var helthcharityemail: TextView
    private lateinit var helthcharitydescription: TextView
    private lateinit var donateBtn:Button
    private lateinit var backToCharitiCate:Button

    override fun onCreate(

        savedInstanceState: Bundle?
    ){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_helth_charity)

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
        helthcharityId = findViewById(R.id.helthcharityId)
        helthcharityname = findViewById(R.id.helthcharityName)
        helthcharityaddress = findViewById(R.id.helthcharitiaddress)
        helthcharitycontact = findViewById(R.id.helthcharityContact)
        helthcharityemail = findViewById(R.id.helthcharityEmail)
        helthcharitydescription = findViewById(R.id.helthcharityDescription)



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
        helthcharityId.text = intent.getStringExtra("helthcharityId")
        helthcharityname.text = intent.getStringExtra("helthcharityName")
        helthcharityaddress.text = intent.getStringExtra("helthcharityAddress")
        helthcharitycontact.text = intent.getStringExtra("helthcharityContact")
        helthcharityemail.text = intent.getStringExtra("helthcharityEmail")
        helthcharitydescription.text = intent.getStringExtra("helthcharityDescription")
    }

}
