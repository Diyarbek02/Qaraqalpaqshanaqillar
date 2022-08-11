package com.example.qaraqalpaqshanaqillar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.ui.Categories.CategoriesFragment
import com.example.qaraqalpaqshanaqillar.ui.Favourites.FavouritesFragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.ui.Search.FragmentSearch
import com.example.qaraqalpaqshanaqillar.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(CategoriesFragment())

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_navigation.setOnItemSelectedListener { item ->
            var fragment: Fragment
            when(item.itemId){
                R.id.maqallar -> {
                    fragment = CategoriesFragment()
                    loadFragment(fragment)
                    true
                }
                R.id.saralanganlar -> {
                    fragment = FavouritesFragment()
                    loadFragment(fragment)
                    true
                }
                R.id.izlew -> {
                    fragment = FragmentSearch()
                    loadFragment(fragment)
                    true
                }

                else -> {
                    false
                }
            }

        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}