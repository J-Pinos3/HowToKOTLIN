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


class FirstFragment : Fragment() {

    private val args:  FirstFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name1

        val tvName1 = view.findViewById<TextView>(R.id.tvName1).apply {
            text = name
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)


        val btnNavigate = root.findViewById<Button>(R.id.btnNavigate)
        btnNavigate.setOnClickListener{
            //findNavController().navigate(R.id.action_firstFragment_to_secondFragment) MEJOR COMO EL CÓDIGOD DE ABAJO
            findNavController().navigate( FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    name = "José Pinos"
                )
            )
        }

        return root
    }

}