package com.example.madproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class  Helth_charity_admin_adpter  (private val HelthList: ArrayList<HelthCharityModel>) :
    RecyclerView.Adapter<Helth_charity_admin_adpter.ViewHolder>() {


    private  lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.helth_chariti_admin_item, parent, false)
        return ViewHolder(itemView,mListener)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentExpence = HelthList[position]
        holder.tvhelthCharityName.text = currentExpence.helthcharityName
    }

    override fun getItemCount(): Int {
        return HelthList.size
    }

    class ViewHolder(itemView: View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val tvhelthCharityName : TextView = itemView.findViewById(R.id.tv_helth_charity_admin_name)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }


    }

}

