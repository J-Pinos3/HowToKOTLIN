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


class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.name

        val tvName2 = view.findViewById<TextView>(R.id.tvName2).apply {
            text = name
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_second, container, false)

        val btnNavigate2 = root.findViewById<Button>(R.id.btnNavigate2)
        btnNavigate2.setOnClickListener {
            findNavController().navigate( SecondFragmentDirections.actionSecondFragmentToThirdFragment(
                    name3 = "Hola Kotlin :3"
                )
            )
        }

        return root
    }

}