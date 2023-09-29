package com.example.testpeletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView

class searchviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchview)

        var users: Array<String> = arrayOf<String>("Alberto","Alvaro","Ana","Amparo","Bartolo",
            "Bernardo","Carla","Carlos","Carolina","Daniela","Domenica");

        var adapterUsers: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,users)

        var svUsers = findViewById<SearchView>(R.id.svUsers)
        var lvUsers = findViewById<ListView>(R.id.lvUsers)

        lvUsers.adapter = adapterUsers
        svUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //query es la consulta
                svUsers.clearFocus()
                if( users.contains(query) ){
                    adapterUsers.filter.filter(query)
                }


                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapterUsers.filter.filter(query)
                return false
            }

        })

    }//ON CREATE




}