package com.example.techastral_loc

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterNgo(var data:ArrayList<dataNgo>,val context: Context):RecyclerView.Adapter<AdapterNgo.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_ngo,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val input = data[position]
        holder.bind(input)

        holder.itemView.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Details")
            builder.setCancelable(false)
            builder.setMessage("Owner : "+input.owner+"\n"+"Username : "+input.owner+"\n"+"Location : "+input.location)
            builder.setPositiveButton("OK"){dialog,which ->
                dialog.dismiss()
            }
            val dialog:AlertDialog = builder.create()
            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.ngoName)
        val moto = v.findViewById<TextView>(R.id.ngoMoto)
        val loc = v.findViewById<TextView>(R.id.ngoLoc)
        fun bind(data:dataNgo){
            name.setText(data.location)
            moto.setText(data.moto)
            loc.setText(data.location)
        }
    }

}