package com.example.qaraqalpaqshanaqillar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.qaraqalpaqshanaqillar.BottomNavFragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(BottomNavFragment())

//        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottom_navigation.setOnItemSelectedListener { item ->
//            val fragment: Fragment
//            when (item.itemId) {
//                R.id.maqallar -> {
//                    fragment = CategoriesFragment()
//                    loadFragment(fragment)
//                    true
//                }
//                R.id.saralanganlar -> {
//                    fragment = FavouritesFragment()
//                    loadFragment(fragment)
//                    true
//                }
//                R.id.izlew -> {
//                    fragment = FragmentSearch()
//                    loadFragment(fragment)
//                    true
//                }
//                R.id.info -> {
//                    fragment = FragmentInfo()
//                    loadFragment(fragment)
//                    true
//                }
//
//                else -> {
//                    false
//                }
//            }
//        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.bottom_nav_container, fragment)
            .commit()
    }
}