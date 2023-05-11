package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add_new_helth_chariti : AppCompatActivity() {
    private lateinit var helthcharityname: EditText
    //  private lateinit var charitycategory: Spinner
    private lateinit var helthcharityaddress: EditText
    private lateinit var helthcharitycontact: EditText
    private lateinit var helthcharityemail: EditText
    private lateinit var helthcharitydescription: EditText
    private lateinit var btnaddcharity: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_helth_chariti)

        helthcharityname = findViewById(R.id.edtText_admin_helth_CharName)
        //charitycategory = findViewById(R.id.spnr_chat_cate)
        helthcharityaddress = findViewById(R.id.edtText_admin_helth_char_address)
        helthcharitycontact = findViewById(R.id.edtText_admin_helth_char_contact)
        helthcharityemail = findViewById(R.id.edtText_admin_helth_char_email)
        helthcharitydescription = findViewById(R.id.edtText_admin_helth_char_description)
        btnaddcharity = findViewById(R.id.btn_btn_admin_add_helth_char)

        helthcharitycontact.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        dbRef = FirebaseDatabase.getInstance().getReference("New helth charity")

        btnaddcharity.setOnClickListener {
            addhelthcharitidata()
        }
    }

    private fun addhelthcharitidata() {
        // getting values
        val helthcharityName = helthcharityname.text.toString()
        // val charityCategory = charitycategory.selectedItem.toString()
        val helthcharityAddress = helthcharityaddress.text.toString()
        val helthcharityContact = helthcharitycontact.text.toString()
        val helthcharityEmail = helthcharityemail.text.toString()
        val helthcharityDescription = helthcharitydescription.text.toString()

        if (helthcharityName.isEmpty()) {
            helthcharityname.error = "please enter name"
        }
//        if (charityCategory.isEmpty()) {
//            // you can use setError method for Spinner instead of error property
//            (charitycategory.selectedView as TextView).error = "please select category"
//        }
        if (helthcharityAddress.isEmpty()) {
            helthcharityaddress.error = "please enter address"
        }
        if (helthcharityContact.isEmpty()) {
            helthcharitycontact.error = "please enter contact"
        }
        if (helthcharityEmail.isEmpty()) {
            helthcharityemail.error = "please enter email "
        }
        if (helthcharityDescription.isEmpty()) {
            helthcharitydescription.error = "please enter description "
        }

        val helthcharityID = dbRef.push().key!!

        val helthcharity = HelthCharityModel(
            helthcharityID,
            helthcharityName,
            //charityCategory,
            helthcharityAddress,
            helthcharityContact,
            helthcharityEmail,
            helthcharityDescription
        )

        dbRef.child(helthcharityID).setValue(helthcharity)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                helthcharityname.text.clear()
                // charitycategory.setSelection(0)
                helthcharityaddress.text.clear()
                helthcharitycontact.text.clear()
                helthcharityemail.text.clear()
                helthcharitydescription.text.clear()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun backToCharities(view: View) {
        val intent = Intent(this, Helth_charity_admin::class.java)
        startActivity(intent)
    }
}
