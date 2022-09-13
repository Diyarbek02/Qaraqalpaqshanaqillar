package com.example.qaraqalpaqshanaqillar.ui.Categories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.MainViewModel
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.SecondViewModel
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentCategoriesBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private lateinit var binding: FragmentCategoriesBinding
    private val adapter = CategoriesAdapter()
    private lateinit var dao: NaqilDao
    private lateinit var viewModel: SecondViewModel

    companion object {
        const val TYPE = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = NaqilDatabase.getInstance(requireContext()).dao()
        viewModel = SecondViewModel(dao)

        binding = FragmentCategoriesBinding.bind(view)

        binding.apply {

            recyclerViewCategories.adapter = adapter

                viewModel.getAllCategories()
            viewModel.naqil.observe(viewLifecycleOwner) {
                adapter.models = it
            }

            searchView.addTextChangedListener {
                it?.let { editable ->
                    val searchValue = editable.toString()

                    viewModel.searchCategories(searchValue)
                    viewModel.naqil.observe(viewLifecycleOwner){
                        adapter.models = it
                    }
                }
            }
        }

        adapter.itemClick {
            val bundle = Bundle()
            bundle.putInt(TYPE, it.type)
            val naqillarFragment = NaqillarFragment()
            naqillarFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, naqillarFragment)
                .addToBackStack(naqillarFragment::class.java.simpleName)
                .commit()
        }
    }
}