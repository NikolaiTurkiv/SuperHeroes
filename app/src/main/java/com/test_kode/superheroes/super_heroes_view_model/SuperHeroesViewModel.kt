package com.test_kode.superheroes.super_heroes_view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.test_kode.data.api.RetrofitInstance
import com.test_kode.data.api.SuperHeroesApiMapper
import com.test_kode.data.database.SuperHeroDatabase
import com.test_kode.data.database.models.SuperHero
import com.test_kode.data.repository.SuperHeroRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SuperHeroesViewModel(private val repositoryImpl: SuperHeroRepositoryImpl,
                           //application:Application
) : ViewModel() {

    private lateinit var disposable: Disposable
    private val superHeroesApiMapper: SuperHeroesApiMapper = SuperHeroesApiMapper()
  //  private lateinit var database: SuperHeroDatabase

    init {
        //database = SuperHeroDatabase.getDatabase(application)
        getSuperHeroesData()
    }

    fun allHeroes() : LiveData<List<SuperHero>> =  repositoryImpl.getAllHeroes().asLiveData()

    fun getAllStudioHeroes(studio:String) : LiveData<List<SuperHero>> = repositoryImpl.getAllStudioHeroes(studio).asLiveData()

    private fun insert(superHeroesList: List<SuperHero>) = viewModelScope.launch {
        repositoryImpl.insert(superHeroesList)
    }

    fun removeRedundant(
        marvelStudio: String,
        dcStudio: String
    ) =viewModelScope.launch {
        repositoryImpl.removeRedundant(marvelStudio, dcStudio)
    }

    fun getSuperHero(name: String): SuperHero = repositoryImpl.getSuperHero(name)

    fun removeAll() = repositoryImpl.removeAll()

    fun getSuperHeroesData() {
        disposable = RetrofitInstance.instance.getAllHeroes()
//            .map { superHero-> superHero.map { superHeroesApiMapper.map(it) } }
//            .repeat()
//            .retry()
//            .delaySubscription(10,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->

                val heroToDb : List<SuperHero> = response.map{
                    superHeroesApiMapper.map(it)!!
                }

                insert(heroToDb)

                heroToDb.forEach {
                 //   Log.d("Heroes",it?.name.toString())
                }

            }, {
                Log.d("Heroes",it.message.toString())
            }, {
            })
    }

    fun dispose(){
        disposable.dispose()
    }
}
class SuperHeroViewModelFactory(private val repository: SuperHeroRepositoryImpl ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperHeroesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuperHeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}