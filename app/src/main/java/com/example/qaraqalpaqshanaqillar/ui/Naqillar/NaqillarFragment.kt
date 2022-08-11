package com.example.qaraqalpaqshanaqillar.ui.Naqillar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.databinding.FragmentNaqillarBinding
import java.security.Key

class NaqillarFragment: Fragment(R.layout.fragment_naqillar) {
    private lateinit var binding: FragmentNaqillarBinding

    companion object{
        const val Key = "key for me"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(Key) ?: 1
    }

}