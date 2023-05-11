package com.example.madproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class Feed : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var appuRecyclerview: RecyclerView


    private lateinit var poslist: ArrayList<admipostmodel>
    private lateinit var dbreff: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_feed, container, false)




        appuRecyclerview = view.findViewById(R.id.rvEmpu)
        appuRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        appuRecyclerview.setHasFixedSize(true)

        poslist = arrayListOf<admipostmodel>()

        getpostdat()






        return view


    }

    private fun getpostdat() {
        appuRecyclerview.visibility = View.GONE



        dbreff = FirebaseDatabase.getInstance().getReference("posts")
        dbreff.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                poslist.clear()
                if (snapshot.exists()) {
                    for (posnap in snapshot.children) {
                        val posdata = posnap.getValue(admipostmodel::class.java)
                        poslist.add(posdata!!)
                    }
                    val pAdapter = userpostaapter(poslist)
                    appuRecyclerview.adapter = pAdapter

                    pAdapter.setonclicklistner(object : userpostaapter.OnItemClickListener {
                        override fun onItemclick(position: Int) {
                            val intent = Intent(requireActivity(),userread::class.java)



                            intent.putExtra("userreads", poslist[position].description)
                            startActivity(intent)
                        }

                    })

                    appuRecyclerview.visibility = View.VISIBLE


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}