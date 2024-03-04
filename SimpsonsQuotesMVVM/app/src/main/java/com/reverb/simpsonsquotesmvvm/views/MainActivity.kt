package com.reverb.simpsonsquotesmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.reverb.simpsonsquotesmvvm.R
import com.reverb.simpsonsquotesmvvm.databinding.ActivityMainBinding
import com.reverb.simpsonsquotesmvvm.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    //https://www.youtube.com/watch?v=dlin2Kp1rdE
    //23:10
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PersonajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =  ViewModelProvider(this)[MainViewModel::class.java]

        setupRecyclerView()


        viewModel.obtenerPersonajes()

        viewModel.listaPersonajes.observe(this){
            adapter.personajesList = it
            adapter.notifyDataSetChanged()
        }

        binding.tilBuscar.setEndIconOnClickListener {
            if( binding.tietBuscar.text.toString() == ""  ){
                viewModel.obtenerPersonajes()
            }else{
                viewModel.obtenerPersonaje( binding.tietBuscar.text.toString() )
            }
        }

    }

    private fun setupRecyclerView() {
        binding.rvPersonajes.layoutManager = GridLayoutManager(this, 3)
        adapter = PersonajeAdapter(this, arrayListOf())
        binding.rvPersonajes.adapter = adapter
    }
}