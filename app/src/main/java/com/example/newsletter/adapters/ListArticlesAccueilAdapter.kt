package com.example.newsletter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.models.Article
import com.example.newsletter.models.ArticleResponse
import kotlinx.coroutines.CoroutineScope

class ListArticlesAccueilAdapter(
    items: ArticleResponse, val handler: ListArticlesAccueilHandler, val context: Context
) : RecyclerView.Adapter<ListArticlesAccueilAdapter.ViewHolder>() {
    private val mArticles: ArticleResponse = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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



    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mArticleImage: ImageView
        val mArticleNameAuthor: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleDescription: TextView
        //val mArticleFavorite: Button

        init {
            // Enable click on item
            mArticleImage = view.findViewById(R.id.image)
            mArticleNameAuthor = view.findViewById(R.id.nameAuthor)
            mArticleTitle = view.findViewById(R.id.title)
            mArticleDate = view.findViewById(R.id.date)
            mArticleDescription = view.findViewById(R.id.description)
           // mArticleFavorite = view.findViewById(R.id.buttonFavoris)
        }
    }

    override fun getItemCount(): Int {
        return mArticles.articles.size
    }


}