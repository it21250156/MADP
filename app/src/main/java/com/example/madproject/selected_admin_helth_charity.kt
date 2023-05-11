package com.example.madproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class selected_admin_helth_charity : AppCompatActivity() {

    private lateinit var helthcharityId: TextView
    private lateinit var helthcharityname: TextView
    private lateinit var helthcharityaddress: TextView
    private lateinit var helthcharitycontact: TextView
    private lateinit var helthcharityemail: TextView
    private lateinit var helthcharitydescription: TextView
    private lateinit var btn_helth_update:Button
    private lateinit var btn_helth_delete:Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_admin_helth_charity)

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

        btn_helth_update.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("helthcharityId").toString(),
                intent.getStringExtra("helthcharityName").toString()
            )
        }

        btn_helth_delete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("helthcharityId").toString()
            )
        }

    }

    private fun deleteRecord(rId: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("New helth charity").child(rId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Charity deleted", Toast.LENGTH_LONG).show()
            finish() // Close this activity and return to the previous activity
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        helthcharityId = findViewById(R.id.helthcharityId)
        helthcharityname = findViewById(R.id.helthcharityName)
        helthcharityaddress = findViewById(R.id.helthcharitiaddress)
        helthcharitycontact = findViewById(R.id.helthcharityContact)
        helthcharityemail = findViewById(R.id.helthcharityEmail)
        helthcharitydescription = findViewById(R.id.helthcharityDescription)
        btn_helth_update = findViewById(R.id.btn_helth_update)
        btn_helth_delete = findViewById(R.id.btn_helth_delete)



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

    private fun openUpdateDialog(
        helthcharityId: String,
        helthcharityName:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView =  inflater.inflate(R.layout.activity_update_helth_charity,null)

        mDialog.setView(mDialogView)

        val helthcharityName = mDialogView.findViewById<EditText>(R.id.ethelthcharityName)
        val helthcharityAddress = mDialogView.findViewById<EditText>(R.id.ethelthcharityAddress)
        val helthcharityContact = mDialogView.findViewById<EditText>(R.id.ethelthcharityContact)
        val helthcharityEmail = mDialogView.findViewById<EditText>(R.id.ethelthcharityEmail)
        val helthcharityDescription = mDialogView.findViewById<EditText>(R.id.ethelthcharityDescription)

        val btn_helth_update = mDialogView.findViewById<Button>(R.id.btn_helth_submit)

        helthcharityName.setText(intent.getStringExtra("helthcharityName").toString())
        helthcharityAddress.setText(intent.getStringExtra("helthcharityAddress").toString())
        helthcharityContact.setText(intent.getStringExtra("helthcharityContact").toString())
        helthcharityEmail.setText(intent.getStringExtra("helthcharityEmail").toString())
        helthcharityDescription.setText(intent.getStringExtra("helthcharityDescription").toString())

        //mDialog.setTitle("Updating $helthcharityName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btn_helth_update.setOnClickListener{
            updatehelthcharityData(
                helthcharityId,
                helthcharityName.text.toString(),
                helthcharityAddress.text.toString(),
                helthcharityContact.text.toString(),
                helthcharityEmail.text.toString(),
                helthcharityDescription.text.toString(),

                )
            Toast.makeText(applicationContext,"Helth Charity Data Updated", Toast.LENGTH_LONG).show()

            //we are adding updated data to our textviews
            helthcharityname.text =  helthcharityName.text.toString()
            helthcharityaddress.text =  helthcharityAddress.text.toString()
            helthcharitycontact.text =  helthcharityContact.text.toString()
            helthcharityemail.text =  helthcharityEmail.text.toString()
            helthcharitydescription.text =  helthcharityDescription.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updatehelthcharityData(
        id:String,
        name:String,
        address:String,
        contact:String,
        email:String,
        description:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("New helth charity").child(id)
        val helthcharitiInfo = HelthCharityModel(id, name, address, contact, email, description)
        dbRef.setValue(helthcharitiInfo)
    }

}
