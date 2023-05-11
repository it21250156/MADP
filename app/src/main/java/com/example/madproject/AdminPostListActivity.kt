package com.example.madproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminPostListActivity : AppCompatActivity() {

    private lateinit var appRecyclerview:RecyclerView


    private lateinit var poslist:ArrayList<admipostmodel>
    private lateinit var dbreff:DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_post_list)

        appRecyclerview=findViewById(R.id.rvEmp)
        appRecyclerview.layoutManager=LinearLayoutManager(this)
        appRecyclerview.setHasFixedSize(true)



        poslist= arrayListOf<admipostmodel>()
        getpostdat()
    }

    private fun getpostdat(){
        appRecyclerview.visibility=View.GONE



        dbreff=FirebaseDatabase.getInstance().getReference("posts")
        dbreff.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                poslist.clear()
                if(snapshot.exists()){
                    for(posnap in snapshot.children){
                        val posdata=posnap.getValue(admipostmodel::class.java)
                        poslist.add(posdata!!)
                    }
                    val pAdapter=postadapter(poslist)
                    appRecyclerview.adapter=pAdapter

                    pAdapter.setonclicklistner(object :postadapter.OnItemClickListener{
                        override fun onItemclick(position: Int) {
                            val intent=Intent(this@AdminPostListActivity,AdminpostdetailsActivity::class.java)

                            intent.putExtra("titleid",poslist[position].titleid)
                            intent.putExtra("title",poslist[position].title)
                            intent.putExtra("cName",poslist[position].charityName)
                            intent.putExtra("descript",poslist[position].description)
                        startActivity(intent)
                        }

                    })

                    appRecyclerview.visibility=View.VISIBLE



                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun addPost(view: View){
        val intent = Intent(this, AdminPostForm::class.java)
        startActivity(intent)
    }
}