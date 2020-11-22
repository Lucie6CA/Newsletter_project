package com.example.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsletter.NavigationListener
import com.example.newsletter.R

class PageAccueilFragment : Fragment(){

    lateinit var buttonAllArticles: Button
    lateinit var buttonByCategory: Button
    lateinit var buttonByCountry: Button
    lateinit var buttonByEditor: Button
    lateinit var buttonFavoris: Button
    lateinit var buttonAPropos: Button



    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.accueil, container, false)


        buttonAllArticles=view.findViewById(R.id.buttonAllArticles)
        buttonByCategory=view.findViewById(R.id.buttonByCategory)
        buttonByCountry=view.findViewById(R.id.buttonByCountry)
        buttonByEditor=view.findViewById(R.id.buttonByEditor)
        buttonFavoris=view.findViewById(R.id.buttonFavoris)
        buttonAPropos=view.findViewById(R.id.buttonAPropos)


        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationListener)?.let{
            it.updateTitle(R.string.accueil)
            it.showInFragment(R.id.frameLayout_ListArticles, ListArticlesAccueilFragment("actuality"))
        }


        buttonAllArticles.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListArticlesFragment("actuality"))
                it.updateTitle(R.string.list_articles)
            }
        }
        buttonByCategory.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListCategoryFragment())
                it.updateTitle(R.string.list_articles)
            }
        }
        buttonByCountry.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListCountryFragment())
                it.updateTitle(R.string.list_articles)
            }
        }
        buttonByEditor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListEditeursFragment())
                it.updateTitle(R.string.list_articles)
            }
        }
        buttonFavoris.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(FavoriteFragment())
                it.updateTitle(R.string.favoris)
            }
        }
        buttonAPropos.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AproposFragment())
                it.updateTitle(R.string.aPropos)
            }
        }

    }

}