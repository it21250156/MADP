package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Helth_charity_admin : AppCompatActivity() {

    private lateinit var  helthcharityRecyclerView : RecyclerView
    private lateinit var HelthList : ArrayList<HelthCharityModel>
    private lateinit var dbRef : DatabaseReference
    //
//    private lateinit var  charityRecyclerView: RecyclerView
//    private lateinit var tvLoadingData:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helth_charity_admin)

        helthcharityRecyclerView = findViewById(R.id.rv_helth_chariti_admin_list)
        helthcharityRecyclerView.layoutManager = LinearLayoutManager(this)

        helthcharityRecyclerView.setHasFixedSize(true)

        HelthList = arrayListOf<HelthCharityModel>()

        gethelthcharitydata()

//    backBtn.setOnClickListener {
//        val fragment = Charities()
//        val transaction = fragmentManager?.beginTransaction()
//        transaction?.replace(R.id.fl_nav,fragment)?.commit()
//    }

//        var buttontwenty = findViewById<Button>(R.id.button20)
//        buttontwenty.setOnClickListener {
//            val intent2 = Intent(this,charity_admin::class.java)
//            startActivity(intent2)
//
//        }
//
//        var buttonfifthteen = findViewById<Button>(R.id.button15)
//        buttonfifthteen.setOnClickListener {
//            val intent1 = Intent (this, add_new_chariti::class.java)
//            startActivity(intent1)
//        }
//
//
//        var buttonsixteen= findViewById<Button>(R.id.button16)
//        buttonsixteen.setOnClickListener {
//            val intent1 = Intent (this, update_charity::class.java)
//            startActivity(intent1)
//        }
    }

    private fun  gethelthcharitydata() {
        helthcharityRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("New helth charity")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                HelthList.clear()
                if (snapshot.exists()){
                    for(helthcharitySnap in snapshot.children) {
                        val helthcharityData = helthcharitySnap.getValue(HelthCharityModel::class.java)
                        HelthList.add(helthcharityData!!)
                    }
                    val mAdapter = Helth_charity_admin_adpter(HelthList)
                    helthcharityRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : Helth_charity_admin_adpter.onItemClickListener{

                        override fun onItemClick(position: Int){
                            val intent = Intent(this@Helth_charity_admin, selected_admin_helth_charity::class.java)

                            //put extras
                            intent.putExtra("helthcharityId",HelthList[position].helthcharityID)
                            intent.putExtra("heltcharityName",HelthList[position].helthcharityName)
                            intent.putExtra("helthcharityAddress",HelthList[position].helthcharityAddress)
                            intent.putExtra("helthcharityContact",HelthList[position].helthcharityContact)
                            intent.putExtra("helthcharityEmail",HelthList[position].helthcharityEmail)
                            intent.putExtra("helthcharityDescription",HelthList[position].helthcharityDescription)

                            startActivity(intent)

                        }


                    })

                    helthcharityRecyclerView.visibility = View.VISIBLE

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }




    fun createCharity(view: View){
        val intent = Intent(this,Add_new_helth_chariti::class.java)
        startActivity(intent)
    }



}