package com.test_kode.superheroes.ui.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test_kode.SuperHeroesApplication
import com.test_kode.data.database.models.SuperHero
import com.test_kode.superheroes.R
import com.test_kode.superheroes.databinding.FragmentSuperHeroesBinding
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroViewModelFactory
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroesViewModel
import com.test_kode.superheroes.ui.adapters.spinner_adapter.SpinnerAdapter
import com.test_kode.superheroes.ui.adapters.superheroes_adapter.SuperHeroesAdapter
import com.test_kode.superheroes.ui.studios.Studios
import com.test_kode.superheroes.ui.utils.changeStudios
import com.test_kode.utils.Constants.Companion.ALl_STUDIOS
import com.test_kode.utils.Constants.Companion.DC_COMICS
import com.test_kode.utils.Constants.Companion.MARVEL


class SuperHeroesFragment : Fragment() {

    private var _binding: FragmentSuperHeroesBinding? = null
    private val binding get() = _binding!!
    private lateinit var superHeroesAdapter: SuperHeroesAdapter
    private lateinit var spinnerAdapter: SpinnerAdapter

    private val superHeroesViewModel: SuperHeroesViewModel by viewModels {
        SuperHeroViewModelFactory((requireActivity().application as SuperHeroesApplication).repositoryImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        spinnerAdapter = SpinnerAdapter(requireContext(), createListStudios())
        superHeroesAdapter = SuperHeroesAdapter(requireContext())

        superHeroesViewModel.allHeroes().observe(viewLifecycleOwner) {
            superHeroesAdapter.updateHeroes(it)
        }

        _binding = FragmentSuperHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.studios.adapter = spinnerAdapter

        val recyclerView = binding.listOfHeroes
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = superHeroesAdapter

        changeStudios(
            binding.studios,
            spinnerAdapter,
            superHeroesViewModel,
            viewLifecycleOwner,
            superHeroesAdapter
        )
        goToHero()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createListStudios(): List<Studios> {
        return listOf(
            Studios(ALl_STUDIOS, R.drawable.all_comix),
            Studios(MARVEL, R.drawable.marvel_logo),
            Studios(DC_COMICS, R.drawable.dc_logo)
        )
    }

    fun goToHero() {
        superHeroesAdapter.onHeroClickListener = object : SuperHeroesAdapter.OnHeroClickListener {
            override fun onHeroClick(superHero: SuperHero) {
                Log.d("QQQQQQ", superHero.name.toString())
                val action =
                    SuperHeroesFragmentDirections.actionSuperHeroesFragmentToSuperHeroesDetailFragment(
                        superHero.name.toString()
                    )
                findNavController().navigate(action)
            }
        }
    }
}