package com.test_kode.superheroes.ui.hero_detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.test_kode.SuperHeroesApplication
import com.test_kode.data.database.models.SuperHero
import com.test_kode.superheroes.databinding.FragmentSuperHeroesDetailBinding
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroViewModelFactory
import com.test_kode.superheroes.super_heroes_view_model.SuperHeroesViewModel
import kotlinx.coroutines.*


class SuperHeroesDetailFragment : Fragment() {

    private var _binding: FragmentSuperHeroesDetailBinding? = null
    private val binding get() = _binding!!
    private var getHeroCoroutine: Job? = null
    private var superHero: SuperHero? = null

    private val superHeroesViewModel: SuperHeroesViewModel by viewModels {
        SuperHeroViewModelFactory((requireActivity().application as SuperHeroesApplication).repositoryImpl)
    }
    private val args: SuperHeroesDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            superHero = superHeroesViewModel.getSuperHero(args.superHeroName)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperHeroesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setData() {
        setInfoToFields()
        animatePprogressBars()
    }

    fun setInfoToFields() {
        with(superHero) {
            binding.superheroName.text = this?.name
            binding.detailRealName.text = this?.fullName
            binding.ocupation.text = this?.occupation
            binding.race.text = this?.race
            binding.plOfBirth.text = this?.placeOfBirth
            Picasso.get().load(this?.imagesLg).into(binding.superheroPhoto)
        }
    }

    fun animatePprogressBars() {
        binding.include.progressIntelligence
        binding.include.progressPower
        binding.include.progressSpeed
        binding.include.progressStrenght

        val animatorIntelligence = superHero?.intelligence?.let {
            ObjectAnimator.ofInt(
                binding.include.progressIntelligence, "progress",
                it, it
            )
        }
        val animatorPower = superHero?.power?.let {
            ObjectAnimator.ofInt(
                binding.include.progressPower, "progress",
                it, it
            )
        }
        val animatorsSpeed = superHero?.speed?.let {
            ObjectAnimator.ofInt(
                binding.include.progressSpeed, "progress",
                it, it
            )
        }
        val animatorStrenght = superHero?.strength?.let {
            ObjectAnimator.ofInt(
                binding.include.progressStrenght, "progress",
                it, it
            )
        }

        animatorIntelligence?.duration = 3000L
        animatorPower?.duration = 3000L
        animatorsSpeed?.duration = 3000L
        animatorStrenght?.duration = 3000L

        animatorIntelligence?.start()
        animatorPower?.start()
        animatorsSpeed?.start()
        animatorStrenght?.start()

    }

}