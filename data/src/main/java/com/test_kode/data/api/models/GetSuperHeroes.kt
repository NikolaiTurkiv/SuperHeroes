package com.test_kode.data.api.models

import android.util.Log
import com.test_kode.data.api.RetrofitInstance
import com.test_kode.data.api.SuperHeroesApiMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GetSuperHeroes(
  //  private val superHeroDatabase: SuperHeroDatabase,
    private val superHeroesApiMapper: SuperHeroesApiMapper
) {

    private lateinit var disposable: Disposable

    fun getSuperHeroesData() {
        disposable = RetrofitInstance.instance.getAllHeroes()
            .map { superHero-> superHero.map { superHeroesApiMapper.map(it) } }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->

                response.forEach {
                    Log.d("Heroes",it?.id.toString())
                }

            }, {
                Log.d("Heroes",it.message.toString())
            }, {
                dispose()
            })
    }

    fun dispose(){
        disposable.dispose()
    }

}