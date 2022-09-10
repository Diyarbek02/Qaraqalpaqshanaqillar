package com.example.qaraqalpaqshanaqillar.ui.Naqillar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import com.example.qaraqalpaqshanaqillar.databinding.FragmentNaqillarBinding
import com.example.qaraqalpaqshanaqillar.ui.Categories.CategoriesFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NaqillarFragment : Fragment(R.layout.fragment_naqillar) {
    private lateinit var binding: FragmentNaqillarBinding
    private val adapter = NaqillarAdapter()
    private lateinit var dao: NaqillarDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = NaqilDatabase.getInstance(requireContext()).naqildao()
        binding = FragmentNaqillarBinding.bind(view)
        val bundle = arguments?.getInt(CategoriesFragment.TYPE) ?: 1
        binding.recyclerViewNaqillar.adapter = adapter
        setData(bundle)

        adapter.setOnClick { naqil ->
            naqil.favourites = 1 - naqil.favourites
            dao.updateNaqil(naqil)
        }
    }

    private fun setData(type: Int) {
        lifecycleScope.launch{
            try {
                val data =  dao.getSelectedNaqillar(type)
                withContext(Dispatchers.Main) {
                    adapter.models = data
                }
            }catch (e: Exception) {
                Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}