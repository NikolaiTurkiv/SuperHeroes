package com.test_kode.superheroes.ui.launch_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test_kode.superheroes.databinding.FragmentLaunchScreenBinding


@SuppressLint("CustomSplashScreen")
class LaunchScreenFragment() : Fragment() {

    private var _binding: FragmentLaunchScreenBinding? = null
    private val binding get() = _binding!!
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaunchScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        countDownTimer = object : CountDownTimer(6000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val action =
                    LaunchScreenFragmentDirections.actionLaunchScreenFragmentToSuperHeroesFragment()
                findNavController().navigate(action)
            }
        }.start()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
        _binding = null
    }


}