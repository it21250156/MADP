package com.example.madproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class  Food_charity_admin_adpter  (private val FoodList: ArrayList<FoodCharityModel>) :
    RecyclerView.Adapter<Food_charity_admin_adpter.ViewHolder>() {


    private  lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_chariti_admin_item, parent, false)
        return ViewHolder(itemView,mListener)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentExpence = FoodList[position]
        holder.tvfoodCharityName.text = currentExpence.foodcharityName
    }

    override fun getItemCount(): Int {
        return FoodList.size
    }

    class ViewHolder(itemView: View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val tvfoodCharityName : TextView = itemView.findViewById(R.id.tv_food_charity_admin_name)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }


    }

}

