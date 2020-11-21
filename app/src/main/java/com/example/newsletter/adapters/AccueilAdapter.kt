package com.example.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.models.Article
import com.example.newsletter.models.ArticleResponse

class AccueilAdapter (
    items:ArticleResponse, val handler: AccueilHandler)
    : RecyclerView.Adapter<AccueilAdapter.ViewHolder>(){

    private val mArticles: ArticleResponse = items






    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val mArticleImage: ImageView
        val mArticleNameAuthor: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleDescription: TextView
        //val mArticleFavorite: ImageButton

        init {
            // Enable click on item
            mArticleImage = view.findViewById(R.id.image)
            mArticleNameAuthor = view.findViewById(R.id.nameAuthor)
            mArticleTitle = view.findViewById(R.id.title)
            mArticleDate = view.findViewById(R.id.date)
            mArticleDescription = view.findViewById(R.id.description)
            // mArticleFavorite = view.findViewById(R.id.item_list_favorite_button)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccueilAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mArticles.articles.size
    }

    override fun onBindViewHolder(holder: AccueilAdapter.ViewHolder, position: Int) {
        val article: Article = mArticles.articles[position]

        //Display Article infos
        holder.mArticleDate.text=article.publishedAt
        holder.mArticleDescription.text=article.description
        holder.mArticleNameAuthor.text=article.author
        holder.mArticleTitle.text=article.title

        //boutton favoris- afficher plein ou creux



        holder.mArticleTitle.setOnClickListener{
            handler.listOpen()
        }

        holder.mArticleDescription.setOnClickListener{
            handler.listOpen()

        }

        //faire favoris

        holder.mArticleImage.setOnClickListener{
            handler.listOpen()
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

}

