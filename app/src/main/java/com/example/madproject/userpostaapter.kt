package com.example.madproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class userpostaapter (private val upostlist:ArrayList<admipostmodel>):
    RecyclerView.Adapter<userpostaapter.ViewHolder>(){

    private lateinit var uplistner: OnItemClickListener
    interface OnItemClickListener{
        fun onItemclick(position: Int)

    }
    fun setonclicklistner(clickListener: OnItemClickListener){
        uplistner=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.postuser,parent,false)
        return ViewHolder(itemView, uplistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentpost=upostlist[position]
        holder.useradminpos.text=currentpost.title
        holder.cname.text=currentpost.charityName
    }

    override fun getItemCount(): Int {
        return upostlist.size
    }
    class ViewHolder(itemView: View, clickListener: userpostaapter.OnItemClickListener): RecyclerView.ViewHolder(itemView) {
       // val tvadminpos: TextView =itemView.findViewById(R.id.tv_admin_post1)
        val useradminpos:TextView=itemView.findViewById(R.id.usertitle)
        val cname:TextView=itemView.findViewById(R.id.userctitle)
        init {
            itemView.setOnClickListener{
                clickListener.onItemclick(adapterPosition)
            }
        }
    }


}