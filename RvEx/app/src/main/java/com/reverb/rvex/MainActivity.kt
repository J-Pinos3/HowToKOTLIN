package com.reverb.rvex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reverb.rvex.adapter.SuperHeroAdapter
import com.reverb.rvex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()


    }//ON CREATE


    private fun initRecyclerView(){
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerSuperHero)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = SuperHeroAdapter(SuperHeroProvider.superHeroList)
        val manager = LinearLayoutManager(this)
        //val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerSuperHero.layoutManager = manager
        binding.recyclerSuperHero.adapter =
            SuperHeroAdapter(SuperHeroProvider.superHeroList) { superHero ->
                onItemSelected(
                    superHero
                )
            }
        //binding.recyclerSuperHero.addItemDecoration(decoration)

    }


    fun onItemSelected(superHero: SuperHero){
        Toast.makeText(this,superHero.superHero, Toast.LENGTH_SHORT).show()
    }
}