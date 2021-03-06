package com.example.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter.R
import com.example.newsletter.models.Pays

class ListCountryAdapter (val handler: SourcesHandler) : RecyclerView.Adapter<ListCountryAdapter.ViewHolder>(){

    val listCountry: MutableList<Pays> = mutableListOf(
        Pays("France","fr"),
        Pays("USA","us"),
        Pays("Argentina","ar"),
        Pays("Australia","au"),
        Pays("Belgique","be"),
        Pays("China","cn"),
        Pays("Japan","jp"),
        Pays("Nigeria","ng"),
        Pays("United Kingdom","gb")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_button, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = listCountry[position]
        holder.mButton.text = country.name
        holder.mButton.setOnClickListener {
            handler.showSourceArticle(country.search)
        }
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mButton: Button


        init {
            // Enable click on item
            mButton = view.findViewById(R.id.itemButton)

        }
    }

}