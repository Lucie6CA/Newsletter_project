package com.example.newsletter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.data.FavDB
import com.example.newsletter.fragments.ListArticlesFragment
import com.example.newsletter.models.Article

class DetailsArticleAdapter(
    private val context: Context, items: Article, val handler: ListArticlesHandler
    ) : RecyclerView.Adapter<DetailsArticleAdapter.ViewHolder>() {
        private val article: Article = items
    private lateinit var favDB: FavDB

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
        holder.mButtonBack.setOnClickListener{handler.back()}
        holder.mButtonFavoris.setOnClickListener{
            if (article.favorite == 0 ){
                holder.mButtonFavoris.setImageResource(R.drawable.ic_coeur_plein)
                article.favorite = 1
               /* favDB.insertIntoTheDatabase(
                    if (article.id!=null) article.id else "",
                    if (article.title!=null) article.title else "",
                    if (article.description!=null) article.description else "",
                    if (article.author!=null) article.author else "",
                    if (article.urlToImage!=null) article.urlToImage else "",
                    if (article.url!=null) article.url else "",
                    1)*/

            }
            else
            {
                article.favorite = 0
               // favDB.remove_fav(article.id)
                holder.mButtonFavoris.setImageResource(R.drawable.ic_coeur_vide)
            }

        }
        holder.mUrlArticle.text =article.url

        holder.mUrlArticle.setOnClickListener{handler.showPage(article.url)}
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


