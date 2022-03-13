package com.example.techastral_loc

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterFundRaiser(var data:ArrayList<dataItem>,val context: Context):RecyclerView.Adapter<AdapterFundRaiser.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_event,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val input = data[position]
        holder.bind(input)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,detail_fund::class.java)
            intent.putExtra("name",input.name)
            intent.putExtra("date",input.date.toString())
            intent.putExtra("type",input.type1)
            intent.putExtra("loc",input.location)
            intent.putExtra("desc",input.desc)
            intent.putExtra("dead",input.endtime)
            intent.putExtra("amt",input.reqdamt)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.eventName)
        val date = v.findViewById<TextView>(R.id.eventDate)
        val desc = v.findViewById<TextView>(R.id.eventDesc)
        fun bind(data: dataItem){
            name.setText(data.name)
            desc.setText(data.desc)
            date.setText(data.date.toString().substringBefore("00:00:00"))
        }
    }

}