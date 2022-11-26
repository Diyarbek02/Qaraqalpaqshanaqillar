package com.example.qaraqalpaqshanaqillar.ui.Categories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentCategoriesBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private val adapter = CategoriesAdapter()
    private lateinit var dao: NaqilDao
    private val binding by lazy { FragmentCategoriesBinding.inflate(layoutInflater) }

    companion object {
        const val TYPE = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = NaqilDatabase.getInstance(requireContext()).dao()

        binding.apply {
            lifecycleScope.launch {
                try {
                    val data = dao.getAllCategories()
                    withContext(Dispatchers.Main) {
                        adapter.models = data
                    }
                }catch (e: Exception) {
                    Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }


            searchView.addTextChangedListener {
                it?.let { editable ->
                    val searchValue = editable.toString()

                    lifecycleScope.launch{
                        try {
                            val data = dao.searchCategories("%$searchValue%")
                            withContext(Dispatchers.Main) {
                                adapter.models = data
                                binding.textNoValue.isVisible = data.isEmpty()
                            }
                        }catch (e: Exception){
                            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            recyclerViewCategories.adapter = adapter
        }

        adapter.itemClick {
            val bundle = Bundle()
            bundle.putInt(TYPE, it.type)
            val naqillarFragment = NaqillarFragment()
            naqillarFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.full_container, naqillarFragment)
                .addToBackStack(naqillarFragment::class.java.simpleName)
                .commit()
        }
    }
}