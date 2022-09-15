package com.example.qaraqalpaqshanaqillar

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
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
        viewModelScope.launch {
            try {
                val response = naqilDao.getAllCategories()
                withContext(Dispatchers.Main){
                    mutablleNaqil.value = response
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun searchCategories(seachValue: String) {
        viewModelScope.launch {
            try {
                val response = naqilDao.searchCategories(seachValue)
                withContext(Dispatchers.Main) {
                    mutablleNaqil.value = response
                }
            }
            catch (e: Exception){

            }
        }
    }
}