package com.test_kode.superheroes

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.test_kode.SuperHeroesApplication
import com.test_kode.data.database.SuperHeroDatabase
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroViewModelFactory
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroesViewModel
import com.test_kode.utils.Constants.Companion.DC_COMICS
import com.test_kode.utils.Constants.Companion.MARVEL
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val superHeroesViewModel: SuperHeroesViewModel by viewModels {
        SuperHeroViewModelFactory((application as SuperHeroesApplication).repositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        superHeroesViewModel.getSuperHeroesData()
    }

    override fun onDestroy() {
        super.onDestroy()
        superHeroesViewModel.dispose()
    }

}
