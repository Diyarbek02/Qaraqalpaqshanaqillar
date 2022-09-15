package com.example.qaraqalpaqshanaqillar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondViewModel(private val naqillarDao: NaqillarDao): ViewModel() {

    private var mutablleNaqillar: MutableLiveData<List<Naqillar>> = MutableLiveData()
    val naqillar: LiveData<List<Naqillar>>
        get() = mutablleNaqillar

    private var mutableUpdate : MutableLiveData<String> = MutableLiveData()
    val update: LiveData<String> = mutableUpdate

    fun getAllNaqillar(){
        viewModelScope.launch {
            try {
            val response = naqillarDao.getAllNaqillar()
            withContext(Dispatchers.Main) {
                mutablleNaqillar.value = response
            }
            }
            catch (e: Exception) {

            }
        }
    }

    fun getSelectedNaqillar(type: Int) {
        viewModelScope.launch {
            try {

                val response = naqillarDao.getSelectedNaqillar(type)
                withContext(Dispatchers.Main) {
                    mutablleNaqillar.value = response
                }
            }
            catch (e: Exception) {

            }
        }
    }

    fun getFavouritesNaqillar() {
        viewModelScope.launch {
            try {
                val response = naqillarDao.getFavouritesNaqillar()
                withContext(Dispatchers.Main) {
                    mutablleNaqillar.value = response
                }
            }
            catch (e:Exception) {

            }
        }
    }

    fun searchNaqillar(searchValue: String) {
        viewModelScope.launch {
            try {
                val response = naqillarDao.searchNaqillar(searchValue)
                withContext(Dispatchers.Main) {
                    mutablleNaqillar.value = response
                }
            } catch (e: Exception) {

            }
        }
    }
}