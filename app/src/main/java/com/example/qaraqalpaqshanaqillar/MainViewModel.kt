package com.example.qaraqalpaqshanaqillar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val naqillarDao: NaqillarDao): ViewModel() {
    private var mutableNaqillar: MutableLiveData<List<Naqillar>> = MutableLiveData()
    val naqillar: LiveData<List<Naqillar>>
    get() = mutableNaqillar

    private var mutableUpdate: MutableLiveData<String> = MutableLiveData()
    val update: LiveData<String> = mutableUpdate

    fun getAllNaqillar() {
        naqillarDao.getAllNaqillar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableNaqillar.value = it
                },
                {

                }
            )
    }

    fun getSelectedNaqillar(type: Int) {
        naqillarDao.getSelectedNaqillar(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableNaqillar.value = it
                },
                {

                }
            )
    }

    fun getFavouritesNaqillar() {
        naqillarDao.getFavouritesNaqillar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableNaqillar.value = it
                },
                {

                }
            )
    }

    fun searchNaqillar(searchValue: String) {
        naqillarDao.searchNaqillar(searchValue)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mutableNaqillar.value = it
                },
                {

                }
            )
    }

    fun updateNaqil(naqil: Naqillar) {
        naqillarDao.updateNaqil(naqil)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mutableUpdate.value = "OK"
            }
    }
}