package com.example.qaraqalpaqshanaqillar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NaqilarViewModel(private val naqillarDao: NaqillarDao): ViewModel() {
    private var mutableNaqillar: MutableLiveData<List<Naqillar>> = MutableLiveData()
    val naqillar: LiveData<List<Naqillar>> get() =mutableNaqillar

    fun getAllNaqillar() {
        viewModelScope.launch {
            try {
                val data = naqillarDao.getAllNaqillar()
                withContext(Dispatchers.Main){
                    mutableNaqillar.value = data
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun getSelectedNaqillar(type: Int){
        viewModelScope.launch {
            try {
                val response = naqillarDao.getSelectedNaqillar(type)
                withContext(Dispatchers.Main){
                    mutableNaqillar.value = response
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun getFavouritesNaqillar() {
        viewModelScope.launch {
            try {
                val data = naqillarDao.getFavouritesNaqillar()
                withContext(Dispatchers.Main){
                    mutableNaqillar.value = data
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun searchNaqillar(searchValue: String) {
        viewModelScope.launch {
            try {
                val data = naqillarDao.searchNaqillar("%$searchValue%")
                withContext(Dispatchers.Main){
                    mutableNaqillar.value = data
                }
            }
            catch (e: Exception){

            }
        }
    }
}