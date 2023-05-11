package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class AdminpostdetailsActivity : AppCompatActivity() {
    private lateinit var titleid: TextView
    private lateinit var title: TextView
    private lateinit var charityName: TextView
    private lateinit var description: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminpostdetails)
        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openupdateialog(
                intent.getStringExtra("titleid").toString(),
                intent.getStringExtra("title").toString()

            )
        }
        btnDelete.setOnClickListener{
            deletepost(
                intent.getStringExtra("titleid").toString()
            )
        }

    }
    private fun deletepost(
        id: String
    ){
        val dbref=FirebaseDatabase.getInstance().getReference("posts").child(id)
        val mtask=dbref.removeValue()
mtask.addOnSuccessListener {
    Toast.makeText(this,"employee data deleted",Toast.LENGTH_LONG).show()

    val intent=Intent(this,AdminPostListActivity::class.java)
    finish()
    startActivity(intent)
}.addOnFailureListener{ error->
    Toast.makeText(this,"deleting err ${error.message}",Toast.LENGTH_SHORT).show()
}

    }


    private fun initView() {
        titleid = findViewById(R.id.titleid)
        title = findViewById(R.id.titlep)
        charityName = findViewById(R.id.cname)
        description = findViewById(R.id.descrpt)

        btnUpdate = findViewById(R.id.btnpostdetUpdate)
        btnDelete = findViewById(R.id.btnpostDelete)
    }


    private fun setValuesToViews() {

        titleid.text = intent.getStringExtra("titleid")
        title.text = intent.getStringExtra("title")
        charityName.text = intent.getStringExtra("cName")
        description.text = intent.getStringExtra("descript")

    }

    private fun openupdateialog(
        titleid: String,
        titles: String
    ) {
        val pdialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val pdialogview = inflater.inflate(R.layout.activity_update_post, null)
        pdialog.setView(pdialogview)

        val titleu = pdialogview.findViewById<EditText>(R.id.uptitle)
        val cname = pdialogview.findViewById<EditText>(R.id.cnameup)
        val descript = pdialogview.findViewById<EditText>(R.id.desup)
        val btnupdt = pdialogview.findViewById<Button>(R.id.btnup)

        titleu.setText(intent.getStringExtra("title").toString())
        cname.setText(intent.getStringExtra("cName").toString())
        descript.setText(intent.getStringExtra("descript").toString())

        pdialog.setTitle("Updating $titles Record")

        val alertDialog = pdialog.create()
        alertDialog.show()

        btnupdt.setOnClickListener {
            updatepostdata(
                titleid,
                titleu.text.toString(),
                cname.text.toString(),
                descript.text.toString()
            )
            Toast.makeText(applicationContext, "post data updated success", Toast.LENGTH_LONG)
                .show()


           title.text=titleu.text.toString()
            charityName.text = cname.text.toString()
            description.text = descript.text.toString()

            alertDialog.dismiss()
        }

    }


    private fun updatepostdata(
        id:String,
        title:String,
        cname:String,
        descript:String
    ){
        val dbref=FirebaseDatabase.getInstance().getReference("posts").child(id)
        val postinfo=admipostmodel(id,title,cname,descript)
        dbref.setValue(postinfo)
    }
}