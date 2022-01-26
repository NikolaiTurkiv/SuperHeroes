package com.test_kode.superheroes

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.test_kode.SuperHeroesApplication
import com.test_kode.data.database.SuperHeroDatabase
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroViewModelFactory
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroesViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val superHeroesViewModel: SuperHeroesViewModel by viewModels {
        SuperHeroViewModelFactory((application as SuperHeroesApplication).repositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  SuperHeroDatabase.getDatabase(this)
        superHeroesViewModel.allHeroes().observe(this){
            Log.d("HEROES",it.size.toString())
            it.forEach {
                Log.d("HEROES",it.name.toString())

            }
        }
    }


}
