package com.example.qaraqalpaqshanaqillar.ui.Categories

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentCategoriesBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarFragment
import com.example.qaraqalpaqshanaqillar.ui.NaqillarActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private lateinit var binding: FragmentCategoriesBinding
    private val adapter = CategoriesAdapter()
    private lateinit var dao: NaqilDao

    companion object {
        const val TYPE = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = NaqilDatabase.getInstance(requireContext()).dao()

        binding = FragmentCategoriesBinding.bind(view)

        binding.apply {

            recyclerViewCategories.adapter = adapter

            dao.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        adapter.models = it
                    },
                    {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                )

            searchView.addTextChangedListener {
                it?.let { editable ->
                    val searchValue = editable.toString()
                    dao.searchCategories("%$searchValue%")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                adapter.models = it
                            },
                            {
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                            }
                        )
                    binding.textNoValue.isVisible = adapter.models.isEmpty()
                }
            }
        }

        adapter.itemClick {
            val bundle = Bundle()
            bundle.putInt(TYPE, it.type)
//            val intent = Intent(getActivity(), NaqillarActivity::class.java)
//            startActivity(intent)
            val naqillarFragment = NaqillarFragment()
            naqillarFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, naqillarFragment)
                .addToBackStack(naqillarFragment::class.java.simpleName)
                .commit()
        }
    }
}