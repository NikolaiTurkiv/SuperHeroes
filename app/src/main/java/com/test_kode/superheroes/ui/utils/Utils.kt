package com.test_kode.superheroes.ui.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.test_kode.data.database.models.SuperHero
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroesViewModel
import com.test_kode.superheroes.ui.adapters.spinner_adapter.SpinnerAdapter
import com.test_kode.superheroes.ui.adapters.superheroes_adapter.SuperHeroesAdapter
import com.test_kode.superheroes.ui.heroes.SuperHeroesFragmentDirections
import com.test_kode.utils.Constants

fun changeStudios(
    spinner: Spinner,
    spinnerAdapter: SpinnerAdapter,
    superHeroesViewModel: SuperHeroesViewModel,
    lifeCycleOwner: LifecycleOwner,
    superHeroesAdapter: SuperHeroesAdapter
) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            when (spinnerAdapter.listOfStudio[p2].name) {
                Constants.ALl_STUDIOS ->
                    superHeroesViewModel.allHeroes().observe(lifeCycleOwner) {
                        superHeroesAdapter.updateHeroes(it)
                    }
                else -> superHeroesViewModel.getAllStudioHeroes(spinnerAdapter.listOfStudio[p2].name)
                    .observe(lifeCycleOwner) {
                        superHeroesAdapter.updateHeroes(it)
                    }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }
}
