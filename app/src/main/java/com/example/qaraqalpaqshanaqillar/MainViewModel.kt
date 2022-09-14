package com.example.qaraqalpaqshanaqillar

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import kotlinx.coroutines.*

class MainViewModel(private val naqilDao: NaqilDao): ViewModel() {
    private var mutablleNaqil: MutableLiveData<List<Naqil>> = MutableLiveData()
    val naqil: LiveData<List<Naqil>>
    get() = mutablleNaqil

    private var mutableUpdate : MutableLiveData<String> = MutableLiveData()
    val update: LiveData<String> = mutableUpdate

    fun getAllCategories() {

    }



}