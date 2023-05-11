package com.example.madproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Food_charity_list : Fragment() {

    private lateinit var  foodcharityRecyclerView : RecyclerView
    private lateinit var FoodList : ArrayList<FoodCharityModel>
    private lateinit var dbRef : DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_charity_list, container, false)
        val backBtn : Button = view.findViewById(R.id.btn_food_back)



        foodcharityRecyclerView = view.findViewById(R.id.rv_food_chariti_list)
        foodcharityRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        foodcharityRecyclerView.setHasFixedSize(true)

        FoodList = arrayListOf<FoodCharityModel>()

        getfoodcharitydata()

        backBtn.setOnClickListener {
            val fragment = Charities()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fl_nav,fragment)?.commit()
        }



        return view
    }
    private fun  getfoodcharitydata() {
        foodcharityRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("New food charity")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                FoodList.clear()
                if (snapshot.exists()){
                    for(charitySnap in snapshot.children) {
                        val foodcharityData = charitySnap.getValue(FoodCharityModel::class.java)
                        FoodList.add(foodcharityData!!)
                    }
                    val mAdapter = Food_charity_adpter(FoodList)
                    foodcharityRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : Food_charity_adpter.onItemClickListener{
                        override fun onItemClick(position: Int){
                            val intent = Intent(requireActivity(), selected_food_charity::class.java)

                            //put extras
                            intent.putExtra("foodcharityId",FoodList [position].foodcharityID)
                            intent.putExtra("foodcharityName",FoodList [position].foodcharityName)
                            intent.putExtra("foodcharityAddress",FoodList [position].foodcharityAddress)
                            intent.putExtra("foodcharityContact",FoodList [position].foodcharityContact)
                            intent.putExtra("foodcharityEmail",FoodList [position].foodcharityEmail)
                            intent.putExtra("foodcharityDescription",FoodList [position].foodcharityDescription)

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



}