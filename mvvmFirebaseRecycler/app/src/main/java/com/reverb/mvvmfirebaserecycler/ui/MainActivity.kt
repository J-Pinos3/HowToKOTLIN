package com.reverb.mvvmfirebaserecycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.reverb.mvvmfirebaserecycler.MainAdapter
import com.reverb.mvvmfirebaserecycler.viemodel.MainViewModel
import com.reverb.mvvmfirebaserecycler.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var container: ShimmerFrameLayout
    private val viewModel by lazy { ViewModelProvider(this@MainActivity).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.shimmer_view_container)
        adapter = MainAdapter()


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeData()


    }

    private fun observeData(){
        container.startShimmer()
        viewModel.fetchUserData().observe(this, Observer {usersDataList ->
            container.stopShimmer()
            container.visibility = View.GONE
            adapter.setListData(usersDataList)
            adapter.notifyDataSetChanged()
        })
    }
}