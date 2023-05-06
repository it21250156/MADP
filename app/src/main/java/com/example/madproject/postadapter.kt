package com.example.madproject

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class postadapter (private val postlist:ArrayList<admipostmodel>):
    RecyclerView.Adapter<postadapter.ViewHolder>(){
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
    val itemView=LayoutInflater.from(parent.context).inflate(R.layout.adminpostitem,parent,false)
       return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentpost=postlist[position]
        holder.tvadminpos.text=currentpost.title
    }



    override fun getItemCount(): Int {
        return postlist.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val tvadminpos:TextView=itemView.findViewById(R.id.tv_admin_post1)
    }

}
