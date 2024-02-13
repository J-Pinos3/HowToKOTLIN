package com.reverb.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class ThirdFragment : Fragment() {

    private val args: ThirdFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name3

        val tvName3 = view.findViewById<TextView>(R.id.tvName3).apply {
            text = name
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.fragment_third, container, false)

        val btnNavigate3 = root.findViewById<Button>(R.id.btnNavigate3)
        btnNavigate3.setOnClickListener {
            findNavController().navigate( ThirdFragmentDirections.actionThirdFragmentToFirstFragment(
                    name1 = "ANDROID MANDA"
                )
            )
        }

        return root
    }


}