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

class Food_charity_admin : AppCompatActivity() {

    private lateinit var  foodcharityRecyclerView : RecyclerView
    private lateinit var FoodList : ArrayList<FoodCharityModel>
    private lateinit var dbRef : DatabaseReference
    //
//    private lateinit var  charityRecyclerView: RecyclerView
//    private lateinit var tvLoadingData:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_charity_admin)

        foodcharityRecyclerView = findViewById(R.id.rv_food_chariti_admin_list)
        foodcharityRecyclerView.layoutManager = LinearLayoutManager(this)

        foodcharityRecyclerView.setHasFixedSize(true)

        FoodList = arrayListOf<FoodCharityModel>()

        getfoodcharitydata()

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

    private fun  getfoodcharitydata() {
        foodcharityRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("New food charity")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                FoodList.clear()
                if (snapshot.exists()){
                    for(foodcharitySnap in snapshot.children) {
                        val foodcharityData = foodcharitySnap.getValue(FoodCharityModel::class.java)
                        FoodList.add(foodcharityData!!)
                    }
                    val mAdapter = Food_charity_admin_adpter(FoodList)
                    foodcharityRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : Food_charity_admin_adpter.onItemClickListener{

                        override fun onItemClick(position: Int){
                            val intent = Intent(this@Food_charity_admin, selected_admin_food_charity::class.java)

                            //put extras
                            intent.putExtra("foodcharityId",FoodList[position].foodcharityID)
                            intent.putExtra("foodcharityName",FoodList[position].foodcharityName)
                            intent.putExtra("foodcharityAddress",FoodList[position].foodcharityAddress)
                            intent.putExtra("foodcharityContact",FoodList[position].foodcharityContact)
                            intent.putExtra("foodcharityEmail",FoodList[position].foodcharityEmail)
                            intent.putExtra("foodcharityDescription",FoodList[position].foodcharityDescription)

                            startActivity(intent)

                        }


                    })

                    foodcharityRecyclerView.visibility = View.VISIBLE

                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }




    fun createCharity(view: View){
        val intent = Intent(this,Add_new_food_chariti::class.java)
        startActivity(intent)
    }



}