package com.example.newsletter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter.R
import com.example.newsletter.fragments.ListEditeursFragment
import com.example.newsletter.models.Editeurs
import com.example.newsletter.models.EditeursResponse

class ListButtonsArticlesAdapter(
    items: EditeursResponse, val handler: ListEditeursFragment, val context: Context
) : RecyclerView.Adapter<ListButtonsArticlesAdapter.ViewHolder>() {

    private val mEditeurs: EditeursResponse = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListButtonsArticlesAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_button, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val editeurs: Editeurs = mEditeurs.editeurs[position]

        //Display Editeur infos
        holder.mButton.text = editeurs.name



        holder.mButton.setOnClickListener {
            handler.showSourceArticle(editeurs.id)
        }


    }


    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val mButton: Button

        init {
            // Enable click on item

            mButton = view.findViewById(R.id.itemButton)
            // mArticleFavorite = view.findViewById(R.id.item_list_favorite_button)
        }
    }

    override fun getItemCount(): Int {
        return mEditeurs.editeurs.size
    }


}