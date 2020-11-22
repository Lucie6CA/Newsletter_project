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
import com.example.newsletter.adapters.ListButtonsArticlesAdapter
import com.example.newsletter.data.EditeurRepository
import com.example.newsletter.models.EditeursResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListEditeursFragment: Fragment() {


    private lateinit var recyclerView: RecyclerView

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_buttons, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewButtons)
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
        getEditor()

    }
    /**
     * Récupère la liste des éditeurs dans un thread secondaire
     */
    private fun getEditor() {
        lifecycleScope.launch(Dispatchers.IO) {
            val editeurs = EditeurRepository.getInstance().getEditeur()
            bindData(editeurs)
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(editeurs: EditeursResponse) {
        lifecycleScope.launch(Dispatchers.Main) {
            //créer l'adapter
            //associer l'adapteur au recyclerview
            val adapter = ListButtonsArticlesAdapter(editeurs,this@ListEditeursFragment,requireContext())
            recyclerView.adapter = adapter
        }
    }


    fun showSourceArticle(source: String) {
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.list_articles)
            it.showFragment(ArticleSourceFragment(source))
        }
        }

   /* override fun back() {
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.list_articles)
        }
        getArticles(subject)
    }
    */



}