package com.example.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter.NavigationListener
import com.example.newsletter.R
import com.example.newsletter.adapters.ListCategoryAdapter
import com.example.newsletter.adapters.ListCountryAdapter
import com.example.newsletter.adapters.SourcesHandler

class ListCategoryFragment : Fragment(), SourcesHandler {
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
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
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
        val adapter = ListCategoryAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun showSourceArticle(source: String) {
        (activity as? NavigationListener)?.let {
            it.showFragment(ArticleCategoryFragment(source))
            it.updateTitle(R.string.list_articles)
        }
    }


}