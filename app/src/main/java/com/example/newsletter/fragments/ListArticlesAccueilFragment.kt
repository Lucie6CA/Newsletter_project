package com.example.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter.NavigationListener
import com.example.newsletter.R
import com.example.newsletter.adapters.ListArticlesAccueilAdapter
import com.example.newsletter.adapters.ListArticlesAccueilHandler
import com.example.newsletter.data.ArticleRepository
import com.example.newsletter.models.ArticleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListArticlesAccueilFragment(subject:String) :Fragment(), ListArticlesAccueilHandler {

        private lateinit var recyclerView: RecyclerView
        val subject = subject
        /**
         * Fonction permettant de définir une vue à attacher à un fragment
         */
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.list_articles, container, false)
            recyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            getArticles(subject)
        }
        /**
         * Récupère la liste des articles dans un thread secondaire
         */
        private fun getArticles(subject: String) {
            lifecycleScope.launch(Dispatchers.IO) {
                val articles = ArticleRepository.getInstance().getArticles(subject)
                bindData(articles)
            }
        }

        /**
         * Rempli le recyclerview avec les données récupérées dans le web service
         * Cette action doit s'effectuer sur le thread principale
         * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
         */
        private fun bindData(articles: ArticleResponse) {
            lifecycleScope.launch(Dispatchers.Main) {
                //créer l'adapter
                //associer l'adapteur au recyclerview
                val adapter = ListArticlesAccueilAdapter(articles,this@ListArticlesAccueilFragment)
                recyclerView.adapter = adapter
            }
        }

        override fun listOpen() {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListArticlesFragment("actuality"))
                it.updateTitle(R.string.list_articles)
            }
        }


}
