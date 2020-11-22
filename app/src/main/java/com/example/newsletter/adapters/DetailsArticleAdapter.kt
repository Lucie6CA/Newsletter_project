package com.example.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.fragments.ListArticlesFragment
import com.example.newsletter.models.Article

class DetailsArticleAdapter(
    items: Article, val handler: ListArticlesHandler
    ) : RecyclerView.Adapter<DetailsArticleAdapter.ViewHolder>() {
        private val article: Article = items
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.details_article, parent, false)
            return ViewHolder(view)
        }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mArticleTitle.text = article.title
        holder.mArticleContent.text = article.content
        holder.mArticleNameAuthor.text = article.author
        holder.mArticleDate.text = article.publishedAt
        holder.mButtonBack.setOnClickListener{
            handler.back()
        }

        holder.mUrlArticle.text =article.url

        holder.mUrlArticle.setOnClickListener{
            handler.showPage(article.url)
        }
        val context= holder.itemView.context
        //diplay image of article
        Glide.with(context)
            .load(article.urlToImage)
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
            .skipMemoryCache(false)
            .into(holder.mArticleImage)
    }
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleImage: ImageView
        val mArticleNameAuthor: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleContent: TextView
        val mButtonBack: ImageButton
        val mButtonFavoris: ImageButton
        val mUrlArticle: TextView

        //val mArticleFavorite: ImageButton

        init {
            // Enable click on item
            mArticleImage = view.findViewById(R.id.imageArticle)
            mArticleNameAuthor = view.findViewById(R.id.auteur)
            mArticleTitle = view.findViewById(R.id.title)
            mArticleDate = view.findViewById(R.id.datepublication)
            mArticleContent = view.findViewById(R.id.descriptionArticle)
            mButtonBack = view.findViewById(R.id.boutonRetour)
            mButtonFavoris = view.findViewById(R.id.coeurfavoris)
            mUrlArticle=view.findViewById(R.id.urlArticle)
            //mArticleFavorite = view.findViewById(R.id.item_list_favorite_button)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

}


