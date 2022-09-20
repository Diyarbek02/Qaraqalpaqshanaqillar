package com.example.qaraqalpaqshanaqillar.viewModel

import androidx.lifecycle.*
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NaqilViewModel(private val naqilDao: NaqilDao): ViewModel() {
    private val  mutableNaqil: MutableLiveData<List<Naqil>> = MutableLiveData()
    val naqil: LiveData<List<Naqil>> get() = mutableNaqil

fun getAllCategoroies(){
    viewModelScope.launch {
        try {
            val data = naqilDao.getAllCategories()
            withContext(Dispatchers.Main) {
                mutableNaqil.value = data
            }
        }catch (e: Exception) {

        }
    }
}

    fun searchCategories(searchValue: String) {
        viewModelScope.launch{
            try {
                val data = naqilDao.searchCategories("%$searchValue%")
                withContext(Dispatchers.Main) {
                    mutableNaqil.value= data
                }
            }catch (e: Exception){

            }
        }
    }

}