package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reverb.recipechallenge.databinding.SavedFragmentLayoutBinding

class SavedFragment: Fragment() {
    private lateinit var binding: SavedFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SavedFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }
}