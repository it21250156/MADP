package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add_new_food_chariti : AppCompatActivity() {
    private lateinit var foodcharityname: EditText
    //  private lateinit var charitycategory: Spinner
    private lateinit var foodcharityaddress: EditText
    private lateinit var foodcharitycontact: EditText
    private lateinit var foodcharityemail: EditText
    private lateinit var foodcharitydescription: EditText
    private lateinit var btnaddcharity: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_food_chariti)

        foodcharityname = findViewById(R.id.edtText_admin_food_CharName)
        //charitycategory = findViewById(R.id.spnr_chat_cate)
        foodcharityaddress = findViewById(R.id.edtText_admin_food_char_address)
        foodcharitycontact = findViewById(R.id.edtText_admin_food_char_contact)
        foodcharityemail = findViewById(R.id.edtText_admin_food_char_email)
        foodcharitydescription = findViewById(R.id.edtText_admin_food_char_description)
        btnaddcharity = findViewById(R.id.btn_btn_admin_add_food_char)

        foodcharitycontact.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        dbRef = FirebaseDatabase.getInstance().getReference("New food charity")

        btnaddcharity.setOnClickListener {
            addfoodcharitidata()
        }
    }

    private fun addfoodcharitidata() {
        // getting values
        val foodcharityName = foodcharityname.text.toString()
        // val charityCategory = charitycategory.selectedItem.toString()
        val foodcharityAddress = foodcharityaddress.text.toString()
        val foodcharityContact = foodcharitycontact.text.toString()
        val foodcharityEmail = foodcharityemail.text.toString()
        val foodcharityDescription = foodcharitydescription.text.toString()

        if (foodcharityName.isEmpty()) {
            foodcharityname.error = "please enter name"
        }
//        if (charityCategory.isEmpty()) {
//            // you can use setError method for Spinner instead of error property
//            (charitycategory.selectedView as TextView).error = "please select category"
//        }
        if (foodcharityAddress.isEmpty()) {
            foodcharityaddress.error = "please enter address"
        }
        if (foodcharityContact.isEmpty()) {
            foodcharitycontact.error = "please enter contact"
        }
        if (foodcharityEmail.isEmpty()) {
            foodcharityemail.error = "please enter email "
        }
        if (foodcharityDescription.isEmpty()) {
            foodcharitydescription.error = "please enter description "
            return
        }

        val foodcharityID = dbRef.push().key!!

        val foodcharity = FoodCharityModel(
            foodcharityID,
            foodcharityName,
            //charityCategory,
            foodcharityAddress,
            foodcharityContact,
            foodcharityEmail,
            foodcharityDescription
        )

        dbRef.child(foodcharityID).setValue(foodcharity)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                foodcharityname.text.clear()
                // charitycategory.setSelection(0)
                foodcharityaddress.text.clear()
                foodcharitycontact.text.clear()
                foodcharityemail.text.clear()
                foodcharitydescription.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun backToCharities(view: View) {
        val intent = Intent(this, Food_charity_admin::class.java)
        startActivity(intent)
    }
}
