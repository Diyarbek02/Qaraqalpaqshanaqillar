package com.example.qaraqalpaqshanaqillar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SecondViewModel(private val naqilDao: NaqilDao): ViewModel() {
    private var mutableNaqil: MutableLiveData<List<Naqil>> = MutableLiveData()
    val naqil: LiveData<List<Naqil>>
    get() =mutableNaqil

    private var mutableUpdate: MutableLiveData<String> = MutableLiveData()
    val update: LiveData<String> = mutableUpdate

    fun getAllCategories() {
        naqilDao.getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableNaqil.value = it
                },
                {

                }
            )
    }

    fun searchCategories(searchValue: String) {
        naqilDao.searchCategories("%$searchValue%")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableNaqil.value = it
                },
                {

                }
            )
    }
}