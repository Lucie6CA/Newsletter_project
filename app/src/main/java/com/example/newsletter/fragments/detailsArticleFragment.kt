package com.example.newsletter.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsletter.NavigationListener
import com.example.newsletter.R
import com.example.newsletter.adapters.DetailsArticleAdapter
import com.example.newsletter.adapters.DetailsArticleHandler
import com.example.newsletter.models.Article

class detailsArticleFragment : Fragment(), DetailsArticleHandler {
    lateinit var textTitle: TextView
    lateinit var nameAuthor: TextView
    lateinit var date: TextView
    lateinit var content: TextView
    lateinit var imageArticle: ImageView
    lateinit var buttonBack: ImageButton
    lateinit var buttonFavoris: ImageButton


    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_article, container, false)


        textTitle=view.findViewById(R.id.title)
        nameAuthor=view.findViewById(R.id.auteur)
        date=view.findViewById(R.id.datepublication)
        content=view.findViewById(R.id.descriptionArticle)
        imageArticle=view.findViewById(R.id.imageArticle)
        buttonBack=view.findViewById(R.id.boutonRetour)
        buttonFavoris=view.findViewById(R.id.coeurfavoris)



        return view
    }
    override fun back() {
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.list_articles)
            it.showFragment(ListArticlesFragment("actuality"))
        }
    }

    override fun showPage(url: String) {
        val chemin: Uri = Uri.parse(url)
        val naviguer = Intent(Intent.ACTION_VIEW,chemin)
        startActivity(naviguer)
    }


}