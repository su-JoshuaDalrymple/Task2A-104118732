package com.example.wishyouwerehere

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

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

        if(LocationList.lastUpdatedIndex != -1)
        {
            val MainLayout = findViewById<View>(R.id.MainActivityLayout)
            val updatedLocation = LocationList.fLocations[LocationList.lastUpdatedIndex].fLocation
            MainLayout?.let {
                Snackbar.make(it, "$updatedLocation Updated!", Snackbar.LENGTH_SHORT)
                    .show()
            }
            LocationList.lastUpdatedIndex = -1
        }
    }

}

