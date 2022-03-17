package com.example.exam6modul.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam6modul.R
import com.example.exam6modul.model.Card

class CardAdapter(val items: ArrayList<Card>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return EessantionalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is EessantionalViewHolder) {

            var number = holder.card_num
            var name = holder.owner_name
            var end = holder.end_date

            number.text = item.card_number.toString()
            name.text = item.owner_name
            end.text = item.end_date



        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class EessantionalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card_num = view.findViewById<TextView>(R.id.tv_card_number)
        val owner_name = view.findViewById<TextView>(R.id.tv_owner_name)
        val end_date = view.findViewById<TextView>(R.id.tv_end_date)


    }
}