package com.example.qaraqalpaqshanaqillar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.databinding.ActivityNaqillarBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarFragment

class NaqillarActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityNaqillarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        binding = ActivityNaqillarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(NaqillarFragment())

    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.full_fragment_container, fragment)
            .commit()
    }
}