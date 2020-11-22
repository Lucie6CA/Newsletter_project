package com.example.newsletter.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.data.FavDB
import com.example.newsletter.models.Article
import com.example.newsletter.models.ArticleResponse
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.*

class ListArticlesAccueilAdapter(
    items: ArticleResponse, val handler: ListArticlesAccueilHandler, val context: Context
) : RecyclerView.Adapter<ListArticlesAccueilAdapter.ViewHolder>() {
    private val mArticles: ArticleResponse = items
    private lateinit var favDB: FavDB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        favDB= FavDB(context)

        val prefs: SharedPreferences =
            context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)
        if (firstStart) {
            createTableOnFirstStart()
        }
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticles.articles[position]

        //Display Article infos
        val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = article.publishedAt
        val dateString = sdfOut.format(date)
        holder.mArticleDate.text = dateString

        holder.mArticleDescription.text=article.description
        holder.mArticleNameAuthor.text=article.author
        holder.mArticleTitle.text=article.title





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

    private fun createTableOnFirstStart() {
        val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }




}