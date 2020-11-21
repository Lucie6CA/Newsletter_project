package com.example.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.newsletter.fragments.AccueilFragment
import com.example.newsletter.fragments.PageAccueilFragment


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
    override fun showInFragment(id:Int, fragment:Fragment){
        supportFragmentManager.beginTransaction().apply{
            replace(id, fragment)
            addToBackStack(null)
        }.commit()
    }






}