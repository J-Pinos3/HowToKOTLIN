package com.reverb.secondmvvmexample.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.reverb.secondmvvmexample.R
import com.reverb.secondmvvmexample.databinding.ActivityMainBinding
import com.reverb.secondmvvmexample.viewmodels.PeliculasViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PeliculasViewModel
    private lateinit var adapterPeliculas: AdapterPeliculas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.cvCartelera
        //https://www.youtube.com/watch?v=GnVdVyqmbo4   11:27

        viewModel = ViewModelProvider(this)[PeliculasViewModel::class.java]

        setUpRecyclerView()

        viewModel.listaPeliculas.observe(this){
            adapterPeliculas.listaPeliculas = it
            adapterPeliculas.notifyDataSetChanged()
        }

        binding.cvCartelera.setOnClickListener {
            viewModel.obtenerCartelera()
            cambiarColorBoton("car")
        }

        binding.cvPopulares.setOnClickListener {
            viewModel.obtenerPopulares()
            cambiarColorBoton("pop")
        }

        viewModel.obtenerCartelera()
    }

    private fun cambiarColorBoton(s: String) {
        when(s){
            "car"->{
                binding.cvCartelera.setCardBackgroundColor(resources.getColor(R.color.verde_200))
                binding.cvPopulares.setCardBackgroundColor(resources.getColor(R.color.azul_200))
            }
            "pop"->{
                binding.cvCartelera.setCardBackgroundColor(resources.getColor(R.color.azul_200))
                binding.cvPopulares.setCardBackgroundColor(resources.getColor(R.color.verde_200))
            }
        }
    }

    private fun setUpRecyclerView() {

        val layoutManager = GridLayoutManager(this, 3)
        binding.rvPeliculas.layoutManager = layoutManager
        adapterPeliculas =  AdapterPeliculas(this, arrayListOf())
        binding.rvPeliculas.adapter = adapterPeliculas

    }


}