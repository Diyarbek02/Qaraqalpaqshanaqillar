package com.example.qaraqalpaqshanaqillar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.qaraqalpaqshanaqillar.databinding.BottomNavBinding

class BottomNavFragment: Fragment(R.layout.bottom_nav) {
    private lateinit var binding: BottomNavBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BottomNavBinding.bind(view)

        /** Jetpack nav */
        val navHostFragment =
            parentFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }
}