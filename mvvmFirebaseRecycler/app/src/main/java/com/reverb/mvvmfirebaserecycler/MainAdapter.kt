package com.reverb.mvvmfirebaserecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class MainAdapter(): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Usuario>()
    fun setListData(data: MutableList<Usuario>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.render(dataList[position])
    }

    override fun getItemCount() = dataList.size




    inner class MainViewHolder(view: View):RecyclerView.ViewHolder (view){

        private val circleImageView = view.findViewById<CircleImageView>(R.id.circleImageView)
        private val txt_title = view.findViewById<TextView>(R.id.txt_title)
        private val txt_desc = view.findViewById<TextView>(R.id.txt_desc)

        fun render(user: Usuario){
            Glide.with(circleImageView.context).load(user.imageUrl).into(circleImageView)
            txt_title.text = user.name
            txt_desc.text = user.description
        }

    }



}