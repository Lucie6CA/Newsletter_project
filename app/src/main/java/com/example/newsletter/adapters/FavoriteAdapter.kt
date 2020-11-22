package com.example.newsletter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.data.FavDB
import com.example.newsletter.models.Article
import com.example.newsletter.models.FavItems
import com.example.newsletter.models.Source
import java.util.*

class FavoriteAdapter(private val context: Context, val handler: ListArticlesHandler, private var favoriteList : MutableList<FavItems>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var favDB: FavDB


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favDB = FavDB(context)
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: FavItems = favoriteList[position]
        var details = Article("", Source("",""),article.author,article.title,
            article.description,article.url, article.urlToImage,Date(),"",1)


        // Display Article infos
        holder.mArticleTitle.text = article.title

        holder.mArticleDescription.text = article.description
        holder.mArticleNameAuthor.text    = article.author

        holder.mArticleFavorite.setImageResource(R.drawable.ic_coeur_plein)

        holder.mArticleTitle.setOnClickListener {
            handler.showArticle(details)
        }
        holder.mArticleDescription.setOnClickListener {
            handler.showArticle(details)
        }
        holder.mArticleFavorite.setOnClickListener(View.OnClickListener {
            article.id?.let { it1 -> favDB.remove_fav(it1) }
            removeItem(position)
        })


        // Display  Avatar
        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.drawable.ic_coeur_plein)
            .error(R.drawable.ic_coeur_plein)
            .skipMemoryCache(false)
            .into(holder.mArticleImage)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleImage: ImageView
        val mArticleNameAuthor: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleDescription: TextView
        val mArticleFavorite: ImageButton

        init {
            // Enable click on item
            mArticleImage = view.findViewById(R.id.image)
            mArticleNameAuthor = view.findViewById(R.id.nameAuthor)
            mArticleTitle = view.findViewById(R.id.title)
            mArticleDate = view.findViewById(R.id.date)
            mArticleDescription = view.findViewById(R.id.description)
            mArticleFavorite = view.findViewById(R.id.buttonFavoris)
        }
    }

    private fun removeItem(position: Int){
        favoriteList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favoriteList.size)
    }
}