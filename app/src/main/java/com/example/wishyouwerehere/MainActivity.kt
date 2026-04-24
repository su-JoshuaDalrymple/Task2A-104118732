package com.example.wishyouwerehere

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locations = findViewById<RecyclerView>(R.id.locationList)
        locations.adapter = LocationListAdapter()
        locations.layoutManager = GridLayoutManager(this, 2)
    }
}

