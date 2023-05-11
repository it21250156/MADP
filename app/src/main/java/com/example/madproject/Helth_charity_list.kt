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

class Helth_charity_list : Fragment() {

    private lateinit var  helthcharityRecyclerView : RecyclerView
    private lateinit var HelthList : ArrayList<HelthCharityModel>
    private lateinit var dbRef : DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_helth_charity_list, container, false)
        val backBtn : Button = view.findViewById(R.id.btn_helth_back)



        helthcharityRecyclerView = view.findViewById(R.id.rv_helth_chariti_list)
        helthcharityRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        helthcharityRecyclerView.setHasFixedSize(true)

        HelthList = arrayListOf<HelthCharityModel>()

        gethelthcharitydata()

        backBtn.setOnClickListener {
            val fragment = Charities()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fl_nav,fragment)?.commit()
        }



        return view
    }
    private fun  gethelthcharitydata() {
        helthcharityRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("New helth charity")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                HelthList.clear()
                if (snapshot.exists()){
                    for(charitySnap in snapshot.children) {
                        val helthcharityData = charitySnap.getValue(HelthCharityModel::class.java)
                        HelthList.add(helthcharityData!!)
                    }
                    val mAdapter = Helth_charity_adpter(HelthList)
                    helthcharityRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : Helth_charity_adpter.onItemClickListener{
                        override fun onItemClick(position: Int){
                            val intent = Intent(requireActivity(), selected_helth_charity::class.java)

                            //put extras
                            intent.putExtra("helthcharityId",HelthList [position].helthcharityID)
                            intent.putExtra("helthcharityName",HelthList [position].helthcharityName)
                            intent.putExtra("helthcharityAddress",HelthList [position].helthcharityAddress)
                            intent.putExtra("helthcharityContact",HelthList [position].helthcharityContact)
                            intent.putExtra("helthcharityEmail",HelthList [position].helthcharityEmail)
                            intent.putExtra("helthcharityDescription",HelthList [position].helthcharityDescription)

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



}