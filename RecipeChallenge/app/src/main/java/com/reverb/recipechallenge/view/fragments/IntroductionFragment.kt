package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.reverb.recipechallenge.databinding.IntroductionFragmentLayoutBinding

class IntroductionFragment: Fragment() {

    private lateinit var binding: IntroductionFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = IntroductionFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            findNavController().navigate(
                IntroductionFragmentDirections.actionIntroductionFragmentToHomeFragment()
            )
        }
    }
}