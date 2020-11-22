package com.example.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.newsletter.fragments.*


class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        // Note that the Toolbar defined in the layout has the id "toolbar"
        toolbar=findViewById(R.id.toolbar)


        setSupportActionBar(toolbar)
        showFragment(PageAccueilFragment())
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }
    override fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
        }.commit()
    }
    override fun showInFragment(id:Int, fragment: Fragment){
        supportFragmentManager.beginTransaction().apply{
            replace(id, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        val searchItem = menu?.findItem(R.id.menu_activity_main_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(p0.isNullOrBlank()){
                }else{
                    showFragment(ListArticlesFragment(p0))
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Gère la sélection d'item
        return when (item.itemId) {
            R.id.menu_activity_main_info -> {
                showFragment(AproposFragment())
                updateTitle(R.string.aPropos)
                true
            }
            R.id.menu_activity_main_fav -> {
                showFragment(FavoriteFragment())
                updateTitle(R.string.favoris)
                true
            }
            R.id.menu_activity_main_home -> {
                showFragment(PageAccueilFragment())
                updateTitle(R.string.accueil)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }








}