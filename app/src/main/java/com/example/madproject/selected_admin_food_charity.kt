package com.example.madproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class selected_admin_food_charity : AppCompatActivity() {

    private lateinit var foodcharityId: TextView
    private lateinit var foodcharityname: TextView
    private lateinit var foodcharityaddress: TextView
    private lateinit var foodcharitycontact: TextView
    private lateinit var foodcharityemail: TextView
    private lateinit var foodcharitydescription: TextView
    private lateinit var btn_food_update:Button
    private lateinit var btn_food_delete:Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_admin_food_charity)

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

        btn_food_update.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("foodcharityId").toString(),
                intent.getStringExtra("foodcharityName").toString()
            )
        }

        btn_food_delete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("foodcharityId").toString()
            )
        }

    }

    private fun deleteRecord(rId: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("New food charity").child(rId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Charity deleted", Toast.LENGTH_LONG).show()
            finish() // Close this activity and return to the previous activity
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        foodcharityId = findViewById(R.id.foodcharityId)
        foodcharityname = findViewById(R.id.foodcharityName)
        foodcharityaddress = findViewById(R.id.foodcharitiaddress)
        foodcharitycontact = findViewById(R.id.foodcharityContact)
        foodcharityemail = findViewById(R.id.foodcharityEmail)
        foodcharitydescription = findViewById(R.id.foodcharityDescription)
        btn_food_update = findViewById(R.id.btn_food_update)
        btn_food_delete = findViewById(R.id.btn_food_delete)



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

    private fun openUpdateDialog(
        foodcharityId: String,
        foodcharityName:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView =  inflater.inflate(R.layout.activity_update_food_charity,null)

        mDialog.setView(mDialogView)

        val foodcharityName = mDialogView.findViewById<EditText>(R.id.etfoodcharityName)
        val foodcharityAddress = mDialogView.findViewById<EditText>(R.id.etfoodcharityAddress)
        val foodcharityContact = mDialogView.findViewById<EditText>(R.id.etfoodcharityContact)
        val foodcharityEmail = mDialogView.findViewById<EditText>(R.id.etfoodcharityEmail)
        val foodcharityDescription = mDialogView.findViewById<EditText>(R.id.etfoodcharityDescription)

        val btn_food_update = mDialogView.findViewById<Button>(R.id.btn_food_submit)

        foodcharityName.setText(intent.getStringExtra("foodcharityName").toString())
        foodcharityAddress.setText(intent.getStringExtra("foodcharityAddress").toString())
        foodcharityContact.setText(intent.getStringExtra("foodcharityContact").toString())
        foodcharityEmail.setText(intent.getStringExtra("foodcharityEmail").toString())
        foodcharityDescription.setText(intent.getStringExtra("foodcharityDescription").toString())

//        mDialog.setTitle("Updating Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btn_food_update.setOnClickListener{
            updatefoodcharityData(
                foodcharityId,
                foodcharityName.text.toString(),
                foodcharityAddress.text.toString(),
                foodcharityContact.text.toString(),
                foodcharityEmail.text.toString(),
                foodcharityDescription.text.toString(),

                )
            Toast.makeText(applicationContext,"Food Charity Data Updated", Toast.LENGTH_LONG).show()

            //we are adding updated data to our textviews
            foodcharityname.text =  foodcharityName.text.toString()
            foodcharityaddress.text =  foodcharityAddress.text.toString()
            foodcharitycontact.text =  foodcharityContact.text.toString()
            foodcharityemail.text =  foodcharityEmail.text.toString()
            foodcharitydescription.text =  foodcharityDescription.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updatefoodcharityData(
        id:String,
        name:String,
        address:String,
        contact:String,
        email:String,
        description:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("New food charity").child(id)
        val foodcharitiInfo = FoodCharityModel(id, name, address, contact, email, description)
        dbRef.setValue(foodcharitiInfo)
    }

}
