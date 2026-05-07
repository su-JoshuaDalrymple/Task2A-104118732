package com.example.wishyouwerehere

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: LocationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locations = findViewById<RecyclerView>(R.id.locationList)
        adapter = LocationListAdapter()
        locations.adapter = adapter
        locations.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}

