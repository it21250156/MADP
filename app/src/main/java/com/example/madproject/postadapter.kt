package com.example.madproject

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class postadapter (private val postlist:ArrayList<admipostmodel>):
    RecyclerView.Adapter<postadapter.ViewHolder>(){

    private lateinit var plistner:OnItemClickListener
    interface OnItemClickListener{
        fun onItemclick(position: Int)

    }
    fun setonclicklistner(clickListener: OnItemClickListener){
        plistner=clickListener
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
    val itemView=LayoutInflater.from(parent.context).inflate(R.layout.adminpostitem,parent,false)
       return ViewHolder(itemView,plistner)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentpost=postlist[position]
        holder.tvadminpos.text=currentpost.title
       // holder.useradminpos.text=currentpost.title
    }



    override fun getItemCount(): Int {
        return postlist.size
    }
    class ViewHolder(itemView: View,clickListener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
            val tvadminpos:TextView=itemView.findViewById(R.id.tv_admin_post1)
        //val useradminpos:TextView=itemView.findViewById(R.id.usertitle)
        init {
            itemView.setOnClickListener{
                clickListener.onItemclick(adapterPosition)
            }
        }
    }

}
