package com.test_kode.superheroes.super_heroes_view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.test_kode.data.api.RetrofitInstance
import com.test_kode.data.api.SuperHeroesApiMapper
import com.test_kode.data.database.models.SuperHero
import com.test_kode.data.repository.SuperHeroRepositoryImpl
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SuperHeroesViewModel(
    private val repositoryImpl: SuperHeroRepositoryImpl
) : ViewModel() {

    private lateinit var disposable: Disposable
    private val superHeroesApiMapper: SuperHeroesApiMapper = SuperHeroesApiMapper()

    fun allHeroes(): LiveData<List<SuperHero>> = repositoryImpl.getAllHeroes().asLiveData()

    fun getAllStudioHeroes(studio: String): LiveData<List<SuperHero>> =
        repositoryImpl.getAllStudioHeroes(studio).asLiveData()

    private fun insertList(superHeroesList: List<SuperHero>) =
        repositoryImpl.insertList(superHeroesList)


    fun getSuperHero(name: String): SuperHero = repositoryImpl.getSuperHero(name)

    fun removeAll() = repositoryImpl.removeAll()

    fun getSuperHeroesData() {
        disposable = RetrofitInstance.instance.getAllHeroes()
            .map { response ->
                response.map {
                    superHeroesApiMapper.map(it)!!
                }
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                insertList(response)
            }, {
                Log.d("Heroes", it.message.toString())
            }, {

            })
    }

    fun dispose() {
        disposable.dispose()
    }
}

class SuperHeroViewModelFactory(private val repository: SuperHeroRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperHeroesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuperHeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}